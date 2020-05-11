package net.kuper.tz.job.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@ApiModel(value = "定时任务")
@Data
public class JobEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private String id;
    /**
     * spring bean名称
     */
    @ApiModelProperty(value = "spring bean名称")
    private String beanName;
    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    private String methodName;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String params;
    /**
     * cron表达式
     */
    @ApiModelProperty(value = "cron表达式")
    private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
    @ApiModelProperty(value = "任务状态  0：正常  1：暂停")
    private Integer status;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 变更时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "变更时间")
    private Date updateTime;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUserId;
    /**
     * 删除： >=1:是 ，0:否
     */
    @ApiModelProperty(value = "删除： >=1:是 ，0:否")
    private Date deleteTime;

}
