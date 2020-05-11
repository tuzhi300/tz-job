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
import java.util.Date;

/**
 * 变更定时任务日志
 *
 * @author kuper
 * @email shengongwen@163.com
 * @date 2020-03-22 12:08:12
 */
@ApiModel(value = "变更定时任务日志")
@Data
public class JobLogUpdateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务日志id
     */
    @NotNull(message = "id为必填", groups = {UpdateGroup.class})
    @ApiModelProperty(value = "任务日志id，最大长度：45", required = true, position = 0 )
    private String id;
    /**
     * 任务id
     */
    @NotNull(message = "任务id为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "任务id不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 45 ,message = "任务id不能超过45个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "任务id，最大长度：45", required = true, position = 1 )
    private String jobId;
    /**
     * spring bean名称
     */
    @NotNull(message = "spring bean名称为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "spring bean名称不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 200 ,message = "spring bean名称不能超过200个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "spring bean名称，最大长度：200", required = true, position = 2 )
    private String beanName;
    /**
     * 方法名
     */
    @NotNull(message = "方法名为必填", groups = {AddGroup.class, UpdateGroup.class})
    @NotBlank(message = "方法名不能为空字符", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max = 100 ,message = "方法名不能超过100个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "方法名，最大长度：100", required = true, position = 3 )
    private String methodName;
    /**
     * 参数
     */
    @Length(max = 2000 ,message = "参数不能超过2,000个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "参数，最大长度：2,000", position = 4 )
    private String params;
    /**
     * 任务状态    0：成功    1：失败
     */
    @NotNull(message = "任务状态    0：成功    1：失败为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "任务状态    0：成功    1：失败", required = true, position = 5 )
    private Integer status;
    /**
     * 失败信息
     */
    @Length(max = 2000 ,message = "失败信息不能超过2,000个字符", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "失败信息，最大长度：2,000", position = 6 )
    private String error;
    /**
     * 耗时(单位：毫秒)
     */
    @NotNull(message = "耗时(单位：毫秒)为必填", groups = {AddGroup.class, UpdateGroup.class})
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
    @NotNull(message = "创建时间为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "创建时间，默认值：CURRENT_TIMESTAMP", required = true, position = 9 )
    private Date createTime;
    /**
     * 删除： >=1:是 ，0:否
     */
    @NotNull(message = "删除： >=1:是 ，0:否为必填", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "删除： >=1:是 ，0:否，默认值：0", required = true, position = 10 )
    private Date deleteTime;

}
