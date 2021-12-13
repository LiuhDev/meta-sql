package com.hlxd.metasql.controller;

import com.hlxd.metasql.common.CodeMsg;
import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.common.ServiceResult;
import com.hlxd.metasql.entity.CreateExistTableInfo;
import com.hlxd.metasql.entity.ModifyTableInfo;
import com.hlxd.metasql.entity.TableInfo;
import com.hlxd.metasql.service.SqlAssembleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：liuhao
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

        ServiceResult serviceResult = sqlAssembleService.createTable(tableInfo);
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

    @PostMapping("/updateTableInfo")
    @ApiOperation(value = "更新数据库信息")
    public Result<?> updateTable(@RequestBody ModifyTableInfo modifyTableInfo) {
        ServiceResult serviceResult = sqlAssembleService.updateTableInfo(modifyTableInfo);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }
    }

    @PostMapping("/addTableField")
    @ApiOperation(value = "新增字段")
    public Result<?> addTableField(@RequestBody TableInfo tableInfo) {
        ServiceResult serviceResult = sqlAssembleService.addTableField(tableInfo);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }
    }

    @DeleteMapping("/deleteTableField")
    @ApiOperation(value = "删除字段")
    public Result<?> deleteTableField(@RequestBody TableInfo tableInfo) {
        ServiceResult serviceResult = sqlAssembleService.deleteTableField(tableInfo);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }
    }

    @PostMapping("/modifyTableField")
    @ApiOperation(value = "修改字段")
    public Result<?> modifyTableField(@RequestBody ModifyTableInfo modifyTableInfo) {
        ServiceResult serviceResult = sqlAssembleService.modifyTableField(modifyTableInfo);
        if (serviceResult.isSuccess()) {
            return Result.success();
        } else {
            return Result.error(CodeMsg.SQLERROR, serviceResult.getInfo());
        }
    }


//    @PostMapping("/createTableByExistTable")
//    @ApiOperation(value = "根据已有数据库表创建新表")
//    public Result<?> createTableByExistTable(@RequestBody CreateExistTableInfo createExistTableInfo) {
//
//        ServiceResult serviceResult = sqlAssembleService.createTableByExistTable(createExistTableInfo);
//    }

}
