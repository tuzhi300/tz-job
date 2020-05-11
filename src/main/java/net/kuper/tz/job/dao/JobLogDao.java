package net.kuper.tz.job.dao;

import net.kuper.tz.job.entity.JobLogEntity;
import net.kuper.tz.job.entity.JobLogQueryEntity;
import net.kuper.tz.job.entity.JobLogUpdateEntity;

import java.util.List;

/**
 * 定时任务日志
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
public interface JobLogDao {

    /**
     * 定时任务日志详情
     *
     * @param id
     * @return
     */
    JobLogEntity queryObject(String id);

    /**
     *  定时任务日志列表
     *
     * @param jobLogQueryEntity
     * @return
     */
    List<JobLogEntity> queryList(JobLogQueryEntity jobLogQueryEntity);

    /**
     * 定时任务日志新增
     *
     * @param jobLogUpdateEntity
     */
    void save(JobLogUpdateEntity jobLogUpdateEntity);

    /**
     *  定时任务日志修改
     *
     * @param jobLogUpdateEntity
     */
    void update(JobLogUpdateEntity jobLogUpdateEntity);

    /**
     * 定时任务日志单条删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 定时任务日志批量删除
     *
     * @param ids
     */
    void deleteBatch(String[] ids);
}
