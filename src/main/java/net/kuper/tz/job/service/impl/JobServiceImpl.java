package net.kuper.tz.job.service.impl;

import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.job.dao.JobDao;
import net.kuper.tz.job.entity.JobEntity;
import net.kuper.tz.job.entity.JobQueryEntity;
import net.kuper.tz.job.entity.JobUpdateEntity;
import net.kuper.tz.job.qrtz.ScheduleStatus;
import net.kuper.tz.job.qrtz.ScheduleUtils;
import net.kuper.tz.job.service.JobService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务Service实现类
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@Slf4j
@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;
    @Autowired
    private Scheduler scheduler;


    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<JobEntity> scheduleJobList = jobDao.queryList(new JobQueryEntity());
        for (JobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public Pagination<JobEntity> queryList(JobQueryEntity jobQueryEntity) {
        PageHelper.startPage(jobQueryEntity.getPage(), jobQueryEntity.getPageSize());
        List<JobEntity> jobList = jobDao.queryList(jobQueryEntity);
        return new Pagination<JobEntity>(jobList);
    }

    @Override
    public JobEntity queryObject(String id) {
        return jobDao.queryObject(id);
    }

    @Override
    public JobEntity save(JobUpdateEntity jobUpdateEntity, String userId) {
        jobUpdateEntity.setStatus(ScheduleStatus.PAUSE.getValue());
        jobUpdateEntity.setCreateUserId(userId);
        jobDao.save(jobUpdateEntity);
        JobEntity entity = jobDao.queryObject(jobUpdateEntity.getId());
        ScheduleUtils.createScheduleJob(scheduler, entity);
        return entity;
    }

    @Override
    public void update(JobUpdateEntity jobUpdateEntity, String userId) {
        jobUpdateEntity.setUpdateUserId(userId);
        jobUpdateEntity.setStatus(null);
        jobDao.update(jobUpdateEntity);
        JobEntity entity = jobDao.queryObject(jobUpdateEntity.getId());
        ScheduleUtils.updateScheduleJob(scheduler, entity);
    }

    @Override
    public void delete(String id, String userId) {
        ScheduleUtils.deleteScheduleJob(scheduler, id);
        jobDao.delete(id, userId);
    }

    @Override
    public void deleteBatch(String[] ids, String userId) {
        if (ids != null) {
            for (String id : ids) {
                ScheduleUtils.deleteScheduleJob(scheduler, id);
            }
        }
        jobDao.deleteBatch(ids, userId);
    }

    @Override
    public void run(String id, String userId) {
        log.info("run as user {}", userId);
        ScheduleUtils.run(scheduler, jobDao.queryObject(id));
    }

    @Override
    public void pause(String id, String userId) {
        ScheduleUtils.pauseJob(scheduler, id);
        JobEntity jobEntity = jobDao.queryObject(id);
        if (jobEntity != null) {
            JobUpdateEntity updateEntity = new JobUpdateEntity();
            updateEntity.setId(id);
            updateEntity.setStatus(ScheduleStatus.PAUSE.getValue());
            updateEntity.setUpdateUserId(userId);
            jobDao.update(updateEntity);

        }
    }

    @Override
    public void resume(String id, String userId) {
        ScheduleUtils.resumeJob(scheduler, id);
        JobEntity jobEntity = jobDao.queryObject(id);
        if (jobEntity != null) {
            JobUpdateEntity updateEntity = new JobUpdateEntity();
            updateEntity.setId(id);
            updateEntity.setStatus(ScheduleStatus.NORMAL.getValue());
            updateEntity.setUpdateUserId(userId);
            jobDao.update(updateEntity);
        }
    }
}
