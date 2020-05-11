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
 * 分页查询定时任务
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@Data
@ApiModel(value = "分页查询定时任务")
public class JobQueryEntity extends PaginationQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * spring bean名称
    */
    @Length(max = 200 ,message = "spring bean名称不能超过200个字符")
    @ApiModelProperty(value = "spring bean名称，最大长度：200", required = true, position = 1 )
    private String beanName;
    /**
    * 方法名
    */
    @Length(max = 100 ,message = "方法名不能超过100个字符")
    @ApiModelProperty(value = "方法名，最大长度：100", required = true, position = 2 )
    private String methodName;
    /**
    * 参数
    */
    @Length(max = 2000 ,message = "参数不能超过2,000个字符")
    @ApiModelProperty(value = "参数，最大长度：2,000", position = 3 )
    private String params;
    /**
    * cron表达式
    */
    @Length(max = 100 ,message = "cron表达式不能超过100个字符")
    @ApiModelProperty(value = "cron表达式，最大长度：100", position = 4 )
    private String cronExpression;
    /**
    * 任务状态  0：正常  1：暂停
    */
    @ApiModelProperty(value = "任务状态  0：正常  1：暂停，默认值：0", required = true, position = 5 )
    private Integer status;
    /**
    * 备注
    */
    @Length(max = 255 ,message = "备注不能超过255个字符")
    @ApiModelProperty(value = "备注，最大长度：255", position = 6 )
    private String remark;
    /**
    * 变更时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "变更时间", position = 7 )
    private Date updateTime;
    /**
    * 修改人
    */
    @ApiModelProperty(value = "修改人", position = 8 )
    private String updateUserId;
    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 9 )
    private Date createTime;
    /**
    * 创建人
    */
    @ApiModelProperty(value = "创建人", position = 10 )
    private String createUserId;
    /**
    * 删除： >=1:是 ，0:否
    */
    @ApiModelProperty(value = "删除： >=1:是 ，0:否，默认值：0", required = true, position = 11 )
    private Date deleteTime;

}
