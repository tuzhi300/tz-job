package net.kuper.tz.job.service;

import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.job.entity.JobLogEntity;
import net.kuper.tz.job.entity.JobLogQueryEntity;
import net.kuper.tz.job.entity.JobLogUpdateEntity;

/**
 * 定时任务日志
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
public interface JobLogService {

    /**
     * 分页查询:定时任务日志
     *
     * @param jobLogQueryEntity 分页查询参数
     * @return Pagination
     */
    Pagination<JobLogEntity> queryList(JobLogQueryEntity jobLogQueryEntity);

    /**
      * 定时任务日志详情查询
      *
      * @param id
      * @return 定时任务日志
      */
    JobLogEntity queryObject(String id);

    /**
     * 新增：定时任务日志
     *
     * @param jobLogUpdateEntity
     * @return 定时任务日志
     */
     JobLogEntity save(JobLogUpdateEntity jobLogUpdateEntity);

    /**
     * 修改 定时任务日志
     *
     * @param jobLogUpdateEntity
     * @return
     */
    void update(JobLogUpdateEntity jobLogUpdateEntity);

    /**
     * 定时任务日志单条根据删除
     *
     * @param id
     * @return
     */
    void delete(String id);

    /**
     * 定时任务日志批量删除
     *
     * @param ids
     * @return
     */
    void deleteBatch(String[] ids);

}

