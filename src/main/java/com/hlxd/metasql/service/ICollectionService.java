package com.hlxd.metasql.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hlxd.metasql.entity.Column;

/**
 * <p>
 * 采集表 服务类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-24
 */
public interface ICollectionService extends IService<Collection> {

    IPage<Collection> getCollectionPage(int current, int size);
}
