package com.hlxd.metasql.controller;

import com.hlxd.metasql.common.CodeMsg;
import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.common.ServiceResult;
import com.hlxd.metasql.entity.TableInfo;
import com.hlxd.metasql.service.SqlAssembleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
