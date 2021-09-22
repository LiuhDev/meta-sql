package com.hlxd.metasql.entity;

import lombok.Data;

import java.util.List;

/**
 * @author ：liuhao
 * @date ：Created in 2021/9/14
 */
@Data
public class ModifyTableInfo {

    /**
     * 新表名
     */
    private String newName;

    /**
     * 旧表名
     */
    private String oldTableName;

    private String test;

    /**
     * 数据库名
     */
    private String databaseName;

    /**
     * 表注释
     */
    private String comment;

    private List<ModifyColumnInfo> modifyColumnInfoList;

}
