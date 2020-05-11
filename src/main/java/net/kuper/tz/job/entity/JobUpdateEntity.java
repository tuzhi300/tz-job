package net.kuper.tz.job.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.kuper.tz.core.validator.group.AddGroup;
import net.kuper.tz.core.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 变更定时任务
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@ApiModel(value = "变更定时任务")
@Data
public class JobUpdateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务id
     */
    @NotNull(message = "id为必填", groups = {UpdateGroup.class})
    @ApiModelProperty(value = "任务id，最大长度：45", required = true, position = 0 )
    private String id;
    /**
     * spring bean名称
     */
    @NotNull(message = "spring bean名称为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "spring bean名称不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 200 ,message = "spring bean名称不能超过200个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "spring bean名称，最大长度：200", required = true, position = 1 )
    private String beanName;
    /**
     * 方法名
     */
    @NotNull(message = "方法名为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "方法名不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 100 ,message = "方法名不能超过100个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "方法名，最大长度：100", required = true, position = 2 )
    private String methodName;
    /**
     * 参数
     */
    @Length(max = 2000 ,message = "参数不能超过2,000个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "参数，最大长度：2,000", position = 3 )
    private String params;
    /**
     * cron表达式
     */
    @Length(max = 100 ,message = "cron表达式不能超过100个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "cron表达式，最大长度：100", position = 4 )
    private String cronExpression;
    /**
     * 任务状态  0：正常  1：暂停
     */
    @NotNull(message = "任务状态  0：正常  1：暂停为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "任务状态  0：正常  1：暂停，默认值：0", required = true, position = 5 )
    private Integer status;
    /**
     * 备注
     */
    @Length(max = 255 ,message = "备注不能超过255个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "备注，最大长度：255", position = 6 )
    private String remark;
    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人", position = 8 )
    private String updateUserId;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人", position = 10 )
    private String createUserId;

}
