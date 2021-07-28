package com.hlxd.metasql.entity;

import lombok.Data;

import java.util.List;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/15
 */
@Data
public class TableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 列信息
     */
    private List<ColumnInfo> columnList;
}
