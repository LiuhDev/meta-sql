package com.hlxd.metasql.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.entity.Table;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 表 服务类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
public interface ITableService extends IService<Table> {

    IPage<Table> getTablePage(int current, int size);

}
