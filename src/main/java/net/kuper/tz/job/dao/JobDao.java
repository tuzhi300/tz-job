package net.kuper.tz.job.dao;

import net.kuper.tz.job.entity.JobEntity;
import net.kuper.tz.job.entity.JobQueryEntity;
import net.kuper.tz.job.entity.JobUpdateEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 定时任务
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
public interface JobDao {

    /**
     * 定时任务详情
     *
     * @param id
     * @return
     */
    JobEntity queryObject(String id);

    /**
     * 定时任务列表
     *
     * @param jobQueryEntity
     * @return
     */
    List<JobEntity> queryList(JobQueryEntity jobQueryEntity);

    /**
     * 定时任务新增
     *
     * @param jobUpdateEntity
     */
    void save(JobUpdateEntity jobUpdateEntity);

    /**
     * 定时任务修改
     *
     * @param jobUpdateEntity
     */
    void update(JobUpdateEntity jobUpdateEntity);

    /**
     * 定时任务单条删除
     *
     * @param id
     */
    void delete(@Param("id") String id, @Param("userId") String userId);

    /**
     * 定时任务批量删除
     *
     * @param ids
     */
    void deleteBatch(@Param("ids") String[] ids, @Param("userId") String userId);
}
