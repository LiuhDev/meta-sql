package com.hlxd.metasql.service;

import com.hlxd.metasql.entity.MetadataMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hlxd.metasql.entity.vo.MenuTreeVO;

import java.util.List;

/**
 * <p>
 * 元数据目录 服务类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-17
 */
public interface IMetadataMenuService extends IService<MetadataMenu> {

    List<MenuTreeVO> getMenuTree();

}
