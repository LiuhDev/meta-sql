package com.hlxd.metasql.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.entity.Column;
import com.hlxd.metasql.service.IColumnService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 字段 前端控制器
 * </p>
 *
 * @author liu hao
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/column")
public class ColumnController {

    @Resource
    private IColumnService columnService;

    @GetMapping("/getColumnPage")
    @ApiOperation("获取列列表")
    public Result<IPage<Column>> getColumnPage(int current, int size, String tableId) {

        IPage<Column> page = columnService.getColumnPage(current, size, tableId);

        return Result.success(page);
    }

    @PostMapping("/addColumn")
    @ApiOperation("新增列")
    public Result<?> addColumn(Column column) {

        boolean result = columnService.save(column);

        return Result.success(result);
    }

    @PostMapping("/editColumn")
    @ApiOperation("编辑列")
    public Result<?> editColumn(@RequestBody Column column) {

        boolean result = columnService.updateById(column);
        return Result.success(result);
    }

    @DeleteMapping("/deleteColumn")
    @ApiOperation("删除列")
    public Result<?> deleteTable(String id) {

        boolean result = columnService.removeById(id);
        return Result.success(result);
    }
}
