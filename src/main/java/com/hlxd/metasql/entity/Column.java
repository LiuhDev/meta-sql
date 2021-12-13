package com.hlxd.metasql.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 字段
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_column")
@ApiModel(value="Column对象", description="字段")
public class Column implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "字段编号")
    private String columnCode;

    @ApiModelProperty(value = "所属表id")
    private String tableId;

    @ApiModelProperty(value = "字段名")
    private String columnName;

    @ApiModelProperty(value = "字段中文名")
    private String columnNameCn;

    @ApiModelProperty(value = "字段类型")
    private String columnType;

    @ApiModelProperty(value = "长度")
    private Integer length;

    @ApiModelProperty(value = "小数点")
    private Integer decimalDigits;

    @ApiModelProperty(value = "精度")
    private Integer numPrecRadix;

    @ApiModelProperty(value = "是否可以为空（0：否，1：是）")
    private Integer nullAble;

    @ApiModelProperty(value = "是否是主键（0：否，1：是）")
    private Integer isPk;

    @ApiModelProperty(value = "创建人ID")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人ID")
    private String editId;

    @ApiModelProperty(value = "更新时间")
    private String editTime;


}
