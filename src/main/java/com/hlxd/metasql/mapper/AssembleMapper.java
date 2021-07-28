package com.hlxd.metasql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/26
 */
@Mapper
public interface AssembleMapper {

    void createTable(@Param("sqlText") String sqlText);
}
