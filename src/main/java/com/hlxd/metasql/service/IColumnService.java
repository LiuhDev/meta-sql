package com.hlxd.metasql.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.entity.Column;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字段 服务类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
public interface IColumnService extends IService<Column> {

    IPage<Column> getColumnPage(int current, int size, String tableId);
}
