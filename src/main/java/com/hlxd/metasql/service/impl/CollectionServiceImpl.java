package com.hlxd.metasql.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlxd.metasql.entity.Collection;
import com.hlxd.metasql.entity.Column;
import com.hlxd.metasql.entity.Table;
import com.hlxd.metasql.mapper.CollectionMapper;
import com.hlxd.metasql.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 采集表 服务实现类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-24
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    @Override
    public IPage<Collection> getCollectionPage(int current, int size) {
        IPage<Collection> page = new Page<>(current, size);
        return baseMapper.getCollectionPage(page);
    }
}
