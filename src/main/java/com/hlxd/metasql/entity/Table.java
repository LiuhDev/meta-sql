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
 * 表
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_table")
@ApiModel(value="Table对象", description="表")
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "表名编号")
    private String tableCode;

    @ApiModelProperty(value = "表名")
    private String tableName;

    @ApiModelProperty(value = "中文表名")
    private String tableNameCn;

    @ApiModelProperty(value = "创建人id")
    private String createId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新人id")
    private String editId;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime editTime;


}
