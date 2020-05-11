package net.kuper.tz.job.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kuper.tz.core.controller.Res;
import net.kuper.tz.core.controller.log.Log;
import net.kuper.tz.core.controller.log.LogType;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.core.validator.ValidatorUtils;
import net.kuper.tz.job.entity.JobLogEntity;
import net.kuper.tz.job.entity.JobLogQueryEntity;
import net.kuper.tz.job.service.JobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 定时任务日志
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@Api(value = "定时任务日志" , description = "定时任务日志" )
@RestController
@RequestMapping("job/joblog" )
public class JobLogController {

    @Autowired
    private JobLogService jobLogService;

    /**
     * 分页查询：定时任务日志
     * @param jobLogQueryEntity
     */
    @Log(type = LogType.QUERY, value = "分页查询定时任务日志")
    @ApiOperation("分页查询定时任务日志" )
    @RequiresPermissions("job:joblog:list" )
    @ResponseBody
    @GetMapping
    public Res<Pagination<JobLogEntity>> list(JobLogQueryEntity jobLogQueryEntity) {
        ValidatorUtils.validateEntity(jobLogQueryEntity);
        Pagination pagination = jobLogService.queryList(jobLogQueryEntity);
        return Res.ok(pagination);
    }


    /**
     * 定时任务日志详情查询
     *
     * @param id
     * @return
     */
    @Log(type = LogType.QUERY, value = "定时任务日志详情查询")
    @ApiOperation("定时任务日志详情查询" )
    @RequiresPermissions("job:joblog:detail" )
    @ResponseBody
    @GetMapping(value = "/{id}" )
    public Res<JobLogEntity> detail(@PathVariable("id") String id) {
        JobLogEntity  jobLog = jobLogService.queryObject(id);
        return Res.ok(jobLog);
    }

//    /**
//     * 添加定时任务日志
//     *
//     * @param jobLogUpdateEntity
//     */
//    @Log(type = LogType.SAVE,value = "添加定时任务日志" )
//    @ApiOperation("添加定时任务日志" )
//    @RequiresPermissions("job:joblog:add" )
//    @ResponseBody
//    @PostMapping
//    public Res<JobLogEntity> save(@RequestBody JobLogUpdateEntity jobLogUpdateEntity) {
//        ValidatorUtils.validateEntity(jobLogUpdateEntity, AddGroup.class);
//        JobLogEntity jobLogEntity =jobLogService.save(jobLogUpdateEntity);
//        return Res.ok(jobLogEntity);
//    }
//
//    /**
//     * 修改定时任务日志
//     * @param jobLogUpdateEntity
//     */
//    @Log(type = LogType.UPDATE,value = "修改定时任务日志" )
//    @ApiOperation("修改定时任务日志" )
//    @RequiresPermissions("job:joblog:update" )
//    @ResponseBody
//    @PutMapping
//    public Res update(@RequestBody JobLogUpdateEntity jobLogUpdateEntity) {
//        ValidatorUtils.validateEntity(jobLogUpdateEntity, UpdateGroup.class);
//        jobLogService.update(jobLogUpdateEntity);
//        return Res.ok();
//    }
//
//    /**
//     * 删除定时任务日志
//     * @param id
//     */
//    @Log(type = LogType.DELETE, value = "删除定时任务日志" )
//    @ApiOperation("删除定时任务日志" )
//    @RequiresPermissions("job:joblog:delete" )
//    @ResponseBody
//    @DeleteMapping(value = "/{id}" )
//    public Res delete(@PathVariable("id") String id) {
//        jobLogService.delete(id);
//        return Res.ok();
//    }

}