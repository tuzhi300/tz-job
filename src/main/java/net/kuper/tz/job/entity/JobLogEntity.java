package net.kuper.tz.job.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 定时任务日志
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@ApiModel(value = "定时任务日志")
@Data
public class JobLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
    @ApiModelProperty(value = "任务日志id")
    private String id;
    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    private String jobId;
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
     * 任务状态    0：成功    1：失败
     */
    @ApiModelProperty(value = "任务状态    0：成功    1：失败")
    private Integer status;
    /**
     * 失败信息
     */
    @ApiModelProperty(value = "失败信息")
    private String error;
    /**
     * 耗时(单位：毫秒)
     */
    @ApiModelProperty(value = "耗时(单位：毫秒)")
    private Integer times;
    /**
     * 执行人
     */
    @ApiModelProperty(value = "执行人")
    private Long execUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 删除： >=1:是 ，0:否
     */
    @ApiModelProperty(value = "删除： >=1:是 ，0:否")
    private Date deleteTime;

}
