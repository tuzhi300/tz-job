package net.kuper.tz.job.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.kuper.tz.core.controller.Res;
import net.kuper.tz.core.controller.auth.UserId;
import net.kuper.tz.core.controller.log.Log;
import net.kuper.tz.core.controller.log.LogType;
import net.kuper.tz.core.mybatis.Pagination;
import net.kuper.tz.core.validator.ValidatorUtils;
import net.kuper.tz.core.validator.group.AddGroup;
import net.kuper.tz.core.validator.group.UpdateGroup;
import net.kuper.tz.job.entity.JobEntity;
import net.kuper.tz.job.entity.JobQueryEntity;
import net.kuper.tz.job.entity.JobUpdateEntity;
import net.kuper.tz.job.service.JobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 定时任务
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@Api(value = "定时任务", description = "定时任务")
@RestController
@RequestMapping("job/job")
public class JobController {

    @Autowired
    private JobService jobService;

    /**
     * 分页查询：定时任务
     *
     * @param jobQueryEntity
     */
    @Log(type = LogType.QUERY, value = "分页查询定时任务")
    @ApiOperation("分页查询定时任务")
    @RequiresPermissions("job:job:list")
    @ResponseBody
    @GetMapping
    public Res<Pagination<JobEntity>> list(JobQueryEntity jobQueryEntity) {
        ValidatorUtils.validateEntity(jobQueryEntity);
        Pagination pagination = jobService.queryList(jobQueryEntity);
        return Res.ok(pagination);
    }


    /**
     * 定时任务详情查询
     *
     * @param id
     * @return
     */
    @Log(type = LogType.QUERY, value = "定时任务详情查询")
    @ApiOperation("定时任务详情查询")
    @RequiresPermissions("job:job:detail")
    @ResponseBody
    @GetMapping(value = "/{id}")
    public Res<JobEntity> detail(@PathVariable("id") String id) {
        JobEntity job = jobService.queryObject(id);
        return Res.ok(job);
    }

    /**
     * 添加定时任务
     *
     * @param jobUpdateEntity
     */
    @Log(type = LogType.SAVE, value = "添加定时任务")
    @ApiOperation("添加定时任务")
    @RequiresPermissions("job:job:add")
    @ResponseBody
    @PostMapping
    public Res<JobEntity> save(@RequestBody JobUpdateEntity jobUpdateEntity, @ApiIgnore @UserId String userId) {
        ValidatorUtils.validateEntity(jobUpdateEntity, AddGroup.class);
        JobEntity jobEntity = jobService.save(jobUpdateEntity, userId);
        return Res.ok(jobEntity);
    }

    /**
     * 修改定时任务
     *
     * @param jobUpdateEntity
     */
    @Log(type = LogType.UPDATE, value = "修改定时任务")
    @ApiOperation("修改定时任务")
    @RequiresPermissions("job:job:update")
    @ResponseBody
    @PutMapping
    public Res update(@RequestBody JobUpdateEntity jobUpdateEntity, @ApiIgnore @UserId String userId) {
        ValidatorUtils.validateEntity(jobUpdateEntity, UpdateGroup.class);
        jobService.update(jobUpdateEntity, userId);
        return Res.ok();
    }

    /**
     * 删除定时任务
     *
     * @param id
     */
    @Log(type = LogType.DELETE, value = "删除定时任务")
    @ApiOperation("删除定时任务")
    @RequiresPermissions("job:job:delete")
    @ResponseBody
    @DeleteMapping(value = "/{id}")
    public Res delete(@PathVariable("id") String id, @ApiIgnore @UserId String userId) {
        jobService.delete(id, userId);
        return Res.ok();
    }

    @Log(type = LogType.OPERATOR, value = "执行定时任务")
    @ApiOperation("执行定时任务")
    @RequiresPermissions("job:job:run")
    @ResponseBody
    @PatchMapping(value = "/run/{id}")
    public Res run(@PathVariable("id") String id, @ApiIgnore @UserId String userId) {
        jobService.run(id, userId);
        return Res.ok();
    }

    @Log(type = LogType.OPERATOR, value = "暂停定时任务")
    @ApiOperation("暂停定时任务")
    @RequiresPermissions("job:job:pause")
    @ResponseBody
    @PatchMapping(value = "/pause/{id}")
    public Res pause(@PathVariable("id") String id, @ApiIgnore @UserId String userId) {
        jobService.pause(id, userId);
        return Res.ok();
    }

    @Log(type = LogType.OPERATOR, value = "重启定时任务")
    @ApiOperation("重启定时任务")
    @RequiresPermissions("job:job:resume")
    @ResponseBody
    @PatchMapping(value = "/resume/{id}")
    public Res resume(@PathVariable("id") String id, @ApiIgnore @UserId String userId) {
        jobService.resume(id, userId);
        return Res.ok();
    }

}