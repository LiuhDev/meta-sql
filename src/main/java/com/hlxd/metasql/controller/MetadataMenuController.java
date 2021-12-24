package com.hlxd.metasql.controller;


import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.entity.MetadataMenu;
import com.hlxd.metasql.entity.Table;
import com.hlxd.metasql.entity.vo.MenuTreeVO;
import com.hlxd.metasql.service.IMetadataMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 元数据目录 前端控制器
 * </p>
 *
 * @author liu hao
 * @since 2021-12-17
 */
@RestController
@RequestMapping("/metadata-menu")
public class MetadataMenuController {

    @Resource
    private IMetadataMenuService metadataMenuService;

    @GetMapping("/getMenuTree")
    @ApiOperation("获取元数据目录树")
    public Result<List<MenuTreeVO>> getMenuTree() {

        List<MenuTreeVO> resultList = metadataMenuService.getMenuTree();

        return Result.success(resultList);
    }

    @PostMapping("/addMenu")
    @ApiOperation("新增目录")
    public Result<?> addMenu(@RequestBody MetadataMenu metadataMenu) {

        boolean result = metadataMenuService.save(metadataMenu);
        return Result.success(result);

    }

    @PostMapping("/editMenu")
    @ApiOperation("编辑目录")
    public Result<?> editMenu(@RequestBody MetadataMenu metadataMenu) {

        boolean result = metadataMenuService.updateById(metadataMenu);
        return Result.success(result);
    }

    @DeleteMapping("/deleteMenu")
    @ApiOperation("删除目录")
    public Result<?> deleteMenu(String id) {

        boolean result = metadataMenuService.removeById(id);

        return Result.success(result);
    }

}
