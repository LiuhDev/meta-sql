package com.hlxd.metasql.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.entity.Column;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字段 Mapper 接口
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
public interface ColumnMapper extends BaseMapper<Column> {

    IPage<Column> getColumnPage(IPage<Column> page, @Param("tableId") String tableId);
}
