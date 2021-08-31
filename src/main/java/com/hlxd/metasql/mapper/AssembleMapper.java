package com.hlxd.metasql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLSyntaxErrorException;
import java.util.Map;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/26
 */
@Mapper
public interface AssembleMapper {

    void executeSql(@Param("sqlText") String sqlText) throws SQLSyntaxErrorException;

    Map<String, String> querySql(@Param("sqlText") String sqlText) throws SQLSyntaxErrorException;
}
