package com.hlxd.metasql.service.impl;

import com.hlxd.metasql.entity.MetadataMenu;
import com.hlxd.metasql.entity.vo.MenuTreeVO;
import com.hlxd.metasql.mapper.MetadataMenuMapper;
import com.hlxd.metasql.service.IMetadataMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 元数据目录 服务实现类
 * </p>
 *
 * @author liu hao
 * @since 2021-12-17
 */
@Service
public class MetadataMenuServiceImpl extends ServiceImpl<MetadataMenuMapper, MetadataMenu> implements IMetadataMenuService {

    @Override
    public List<MenuTreeVO> getMenuTree() {
        //拿到菜单的所有数据
        List<MetadataMenu> allData = list();
        List<MenuTreeVO> rootList = new ArrayList<>();
        //遍历所有数据，找到根节点菜单
        for (MetadataMenu metadataMenu : allData) {
            if (metadataMenu.getPid().equals("0")) {
                //添加到根节点的列表中
                MenuTreeVO menuTreeVO = new MenuTreeVO();
                BeanUtils.copyProperties(metadataMenu, menuTreeVO);
                //找到根节点菜单的时候，寻找这个根节点菜单下的子节点菜单。
                findChildren(menuTreeVO, allData);
                rootList.add(menuTreeVO);
            }
        }
        return rootList;

    }


    private void findChildren(MenuTreeVO root, List<MetadataMenu> allData) {

        List<MenuTreeVO> childrenList = new ArrayList<>();
        //遍历所有数据，找到是入参父节点的子节点的数据，然后加到childrenList集合中。
        for (MetadataMenu menu : allData) {
            if (root.getId().equals(menu.getPid())) {
                MenuTreeVO menuTreeVO = new MenuTreeVO();
                BeanUtils.copyProperties(menu, menuTreeVO);
                childrenList.add(menuTreeVO);
            }
        }
        //若子节点不存在，那么就不必再遍历子节点中的子节点了 直接返回。
        if (childrenList.size() == 0)
            return;
        //设置父节点的子节点列表
        root.setSubMenu(childrenList);
        //若子节点存在，接着递归调用该方法，寻找子节点的子节点。
        for (MenuTreeVO child : childrenList) {
            findChildren(child, allData);
        }
    }
}
