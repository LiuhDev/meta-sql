package com.hlxd.metasql.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlxd.metasql.entity.Table;
import com.hlxd.metasql.mapper.TableMapper;
import com.hlxd.metasql.service.ITableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 表 服务实现类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

    @Override
    public IPage<Table> getTablePage(int current, int size) {

        IPage<Table> page = new Page<>(current, size);
        return baseMapper.getTablePage(page);
    }
}
