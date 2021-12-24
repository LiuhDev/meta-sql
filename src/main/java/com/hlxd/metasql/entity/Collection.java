package com.hlxd.metasql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 采集表
 * </p>
 *
 * @author liu hao
 * @since 2021-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_collection")
@ApiModel(value="Collection对象", description="采集表")
public class Collection implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "数据源名称")
    private String dataSourceName;

    @ApiModelProperty(value = "数据源挂载路径")
    private String dataSourcePath;

    @ApiModelProperty(value = "数据源采集模式")
    private String collectionMode;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "驱动")
    private String driver;

    @ApiModelProperty(value = "数据访问url")
    private String dataSourceUrl;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "库名")
    private String databaseName;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人id")
    private String editId;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime editTime;


}
