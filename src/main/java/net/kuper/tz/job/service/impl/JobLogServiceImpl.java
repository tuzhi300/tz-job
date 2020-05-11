package net.kuper.tz.job.service.impl;

import com.github.pagehelper.PageHelper;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.job.dao.JobLogDao;
import net.kuper.tz.job.entity.JobLogEntity;
import net.kuper.tz.job.entity.JobLogQueryEntity;
import net.kuper.tz.job.entity.JobLogUpdateEntity;
import net.kuper.tz.job.service.JobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 定时任务日志Service实现类
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@Service("jobLogService" )
public class JobLogServiceImpl implements JobLogService {

    @Autowired
    private JobLogDao jobLogDao;

    @Override
    public Pagination<JobLogEntity> queryList(JobLogQueryEntity jobLogQueryEntity) {
        PageHelper.startPage(jobLogQueryEntity.getPage(), jobLogQueryEntity.getPageSize());
        List<JobLogEntity> jobLogList = jobLogDao.queryList(jobLogQueryEntity);
        return new Pagination<JobLogEntity>(jobLogList);
    }

    @Override
    public JobLogEntity queryObject(String id) {
        return jobLogDao.queryObject(id);
    }

    @Override
    public JobLogEntity save(JobLogUpdateEntity jobLogUpdateEntity) {
        jobLogDao.save(jobLogUpdateEntity);
        return jobLogDao.queryObject(jobLogUpdateEntity.getId());
    }

    @Override
    public void update(JobLogUpdateEntity jobLogUpdateEntity) {
        jobLogDao.update(jobLogUpdateEntity);
    }

    @Override
    public void delete(String id) {
        jobLogDao.delete(id);
    }

    @Override
    public void deleteBatch(String[] ids) {
        jobLogDao.deleteBatch(ids);
    }
}
