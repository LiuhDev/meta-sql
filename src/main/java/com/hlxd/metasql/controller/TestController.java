package com.hlxd.metasql.controller;

import com.hlxd.metasql.common.CodeMsg;
import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.common.ServiceResult;
import com.hlxd.metasql.entity.TableInfo;
import com.hlxd.metasql.service.SqlAssembleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/5
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Api("测试API接口")
public class TestController {

    @Resource
    private SqlAssembleService sqlAssembleService;

    @PostMapping("/createTable")
    @ApiOperation(value = "新建数据表")
    public Result<?> createTable(@RequestBody TableInfo tableInfo) {

        ServiceResult serviceResult = sqlAssembleService.createTable(tableInfo, null);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }

    }

    @DeleteMapping("/dropTable/{tableName}")
    @ApiOperation(value = "删除数据表")
    public Result<?> dropTable(@PathVariable String tableName) {

        ServiceResult serviceResult = sqlAssembleService.dropTable(tableName, null);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }
    }

    @PostMapping("/updateTable")
    @ApiOperation(value = "更新数据库")
    public Result<?> updateTable(@RequestBody TableInfo tableInfo) {
        ServiceResult serviceResult = sqlAssembleService.updateTable(tableInfo, null);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }
    }

}
