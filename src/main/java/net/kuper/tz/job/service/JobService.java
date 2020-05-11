package net.kuper.tz.job.service;

import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.job.entity.JobEntity;
import net.kuper.tz.job.entity.JobQueryEntity;
import net.kuper.tz.job.entity.JobUpdateEntity;

/**
 * 定时任务
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
public interface JobService {

    /**
     * 分页查询:定时任务
     *
     * @param jobQueryEntity 分页查询参数
     * @return Pagination
     */
    Pagination<JobEntity> queryList(JobQueryEntity jobQueryEntity);

    /**
     * 定时任务详情查询
     *
     * @param id
     * @return 定时任务
     */
    JobEntity queryObject(String id);

    /**
     * 新增：定时任务
     *
     * @param jobUpdateEntity
     * @return 定时任务
     */
    JobEntity save(JobUpdateEntity jobUpdateEntity, String userId);

    /**
     * 修改 定时任务
     *
     * @param jobUpdateEntity
     * @return
     */
    void update(JobUpdateEntity jobUpdateEntity, String userId);

    /**
     * 定时任务单条根据删除
     *
     * @param id
     * @return
     */
    void delete(String id, String userId);

    /**
     * 定时任务批量删除
     *
     * @param ids
     * @return
     */
    void deleteBatch(String[] ids, String userId);

    /**
     * 执行任务
     *
     * @param id
     * @param userId
     */
    void run(String id, String userId);

    /**
     * 暂停
     *
     * @param id
     * @param userId
     */
    public void pause(String id, String userId);

    /**
     * 重启
     *
     * @param id
     * @param userId
     */
    public void resume(String id, String userId);

}

