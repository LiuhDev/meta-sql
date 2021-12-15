package com.hlxd.metasql.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlxd.metasql.entity.Column;
import com.hlxd.metasql.mapper.ColumnMapper;
import com.hlxd.metasql.service.IColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字段 服务实现类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
@Service
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements IColumnService {

    @Override
    public IPage<Column> getColumnPage(int current, int size, String tableId) {

        IPage<Column> page = new Page<>(current, size);
        return baseMapper.getColumnPage(page, tableId);
    }
}
