package com.hlxd.metasql.entity;

import lombok.Data;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/15
 */
@Data
public class ColumnInfo {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列类型
     */
    private String columnType;

    /**
     * 默认值
     */
    private String defaultValue;

    /**
     * 列备注
     */
    private String columnComment;

    /**
     * 是否允许为空
     */
    private Boolean isNull;

    /**
     * 是否自增
     */
    private Boolean isAutoInc;

    /**
     * 是否唯一
     */
    private Boolean isUnique;

    /**
     * 是否为主键
     */
    private Boolean isPk;

}
