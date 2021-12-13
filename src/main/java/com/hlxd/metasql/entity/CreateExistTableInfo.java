package com.hlxd.metasql.entity;

import lombok.Data;

import java.util.List;

@Data
public class CreateExistTableInfo {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 来源表
     */
    private List<TableInfo> tableList;
}
