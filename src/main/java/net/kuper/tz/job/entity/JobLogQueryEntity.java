package net.kuper.tz.job.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.kuper.tz.core.mybatis.PaginationQuery;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/**
 * 分页查询定时任务日志
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@Data
@ApiModel(value = "分页查询定时任务日志")
public class JobLogQueryEntity extends PaginationQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 任务id
    */
    @Length(max = 45 ,message = "任务id不能超过45个字符")
    @ApiModelProperty(value = "任务id，最大长度：45", required = true, position = 1 )
    private String jobId;
    /**
    * spring bean名称
    */
    @Length(max = 200 ,message = "spring bean名称不能超过200个字符")
    @ApiModelProperty(value = "spring bean名称，最大长度：200", required = true, position = 2 )
    private String beanName;
    /**
    * 方法名
    */
    @Length(max = 100 ,message = "方法名不能超过100个字符")
    @ApiModelProperty(value = "方法名，最大长度：100", required = true, position = 3 )
    private String methodName;
    /**
    * 参数
    */
    @Length(max = 2000 ,message = "参数不能超过2,000个字符")
    @ApiModelProperty(value = "参数，最大长度：2,000", position = 4 )
    private String params;
    /**
    * 任务状态    0：成功    1：失败
    */
    @ApiModelProperty(value = "任务状态    0：成功    1：失败", required = true, position = 5 )
    private Integer status;
    /**
    * 失败信息
    */
    @Length(max = 2000 ,message = "失败信息不能超过2,000个字符")
    @ApiModelProperty(value = "失败信息，最大长度：2,000", position = 6 )
    private String error;
    /**
    * 耗时(单位：毫秒)
    */
    @ApiModelProperty(value = "耗时(单位：毫秒)", required = true, position = 7 )
    private Integer times;
    /**
    * 执行人
    */
    @ApiModelProperty(value = "执行人", position = 8 )
    private Long execUserId;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 9 )
    private Date createTime;
    /**
    * 删除： >=1:是 ，0:否
    */
    @ApiModelProperty(value = "删除： >=1:是 ，0:否，默认值：0", required = true, position = 10 )
    private Date deleteTime;

}
