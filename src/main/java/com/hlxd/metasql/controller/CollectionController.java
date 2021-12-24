package com.hlxd.metasql.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hlxd.metasql.common.Result;
import com.hlxd.metasql.entity.Collection;
import com.hlxd.metasql.entity.Column;
import com.hlxd.metasql.service.ICollectionService;
import com.hlxd.metasql.utils.DatabaseMetadataUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 采集表 前端控制器
 * </p>
 *
 * @author liu hao
 * @since 2021-12-24
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Resource
    private ICollectionService collectionService;

    @GetMapping("/getAllTable")
    @ApiOperation("获取制定数据库所有表信息")
    public Result<?> getAllTable(Collection collection) {

        DatabaseMetadataUtils metaDataUtils =
                new DatabaseMetadataUtils("meta_sql",
                        "com.mysql.cj.jdbc.Driver",
                        "jdbc:mysql://10.10.96.214:3306/",
                        "root",
                        "Hlxd@123");
        metaDataUtils.getAllTableList();
        metaDataUtils.closeCon();
        return Result.success();
    }

    @GetMapping("/getCollectionPage")
    @ApiOperation("获取采集列表")
    public Result<IPage<Collection>> getCollectionPage(int current, int size) {

        IPage<Collection> page = collectionService.getCollectionPage(current, size);

        return Result.success(page);
    }

}
