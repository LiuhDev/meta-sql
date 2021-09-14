package com.hlxd.metasql.mapper;

import com.hlxd.metasql.entity.ColumnInfo;
import com.hlxd.metasql.entity.ModifyColumnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Map;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/26
 */
@Mapper
public interface AssembleMapper {

    public void executeSql(@Param("sqlText") String sqlText) throws SQLSyntaxErrorException;

    public void addTableField(@Param("tableName") String tableName, @Param("columns") ColumnInfo columns);

    public void deleteTableField(@Param("tableName") String tableName, @Param("columns") ColumnInfo columns);

    public void modifyTableField(@Param("tableName") String tableName, @Param("columns") ModifyColumnInfo columns);
}
