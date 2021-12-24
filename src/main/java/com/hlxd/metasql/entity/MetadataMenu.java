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
 * 元数据目录
 * </p>
 *
 * @author liu hao
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_metadata_menu")
@ApiModel(value="MetadataMenu对象", description="元数据目录")
public class MetadataMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "父id")
    private String pid;

    @ApiModelProperty(value = "节点名称")
    private String name;

    @ApiModelProperty(value = "排序号")
    private Integer sortNo;

    @ApiModelProperty(value = "（0：正常，1：停用）")
    private Integer status;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人id")
    private String editId;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime editTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
