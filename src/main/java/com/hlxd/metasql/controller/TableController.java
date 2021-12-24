package com.hlxd.metasql.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.entity.Table;
import com.hlxd.metasql.service.ITableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 表 前端控制器
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/table")
public class TableController {

    @Resource
    private ITableService tableService;

    @GetMapping("/getTablePage")
    @ApiOperation("获取表分页列表")
    public Result<IPage<Table>> getTablePage(int current, int size) {

        IPage<Table> page = tableService.getTablePage(current, size);
        return Result.success(page);
    }

    @PostMapping("/addTable")
    @ApiOperation("新增表")
    public Result<?> addTable(@RequestBody Table table) {

        boolean result = tableService.save(table);
        return Result.success(result);

    }

    @PostMapping("/editTable")
    @ApiOperation("编辑表")
    public Result<?> editTable(@RequestBody Table table) {

        boolean result = tableService.updateById(table);
        return Result.success(result);
    }

    @DeleteMapping("/deleteTable")
    @ApiOperation("删除表")
    public Result<?> deleteTable(String id) {

        boolean result = tableService.removeById(id);

        return Result.success(result);
    }

}
