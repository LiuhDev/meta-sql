package com.hlxd.metasql.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 采集表 Mapper 接口
 * </p>
 *
 * @author liu hao
 * @since 2021-12-24
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    IPage<Collection> getCollectionPage(IPage<Collection> page);
}
