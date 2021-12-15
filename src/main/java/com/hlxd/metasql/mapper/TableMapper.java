package com.hlxd.metasql.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.entity.Table;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 表 Mapper 接口
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
public interface TableMapper extends BaseMapper<Table> {

    IPage<Table> getTablePage(IPage<Table> page);
}
