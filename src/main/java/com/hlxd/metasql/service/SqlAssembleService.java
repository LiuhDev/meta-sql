package com.hlxd.metasql.service;

import cn.hutool.core.util.StrUtil;
import com.hlxd.metasql.common.ServiceResult;
import com.hlxd.metasql.entity.ColumnInfo;
import com.hlxd.metasql.entity.ModifyColumnInfo;
import com.hlxd.metasql.entity.ModifyTableInfo;
import com.hlxd.metasql.entity.TableInfo;
import com.hlxd.metasql.mapper.AssembleMapper;
import com.hlxd.metasql.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.SQLSyntaxErrorException;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/15
 */
@Service
@Slf4j
public class SqlAssembleService {

    @Resource
    private AssembleMapper assembleMapper;


    @Transactional(rollbackFor = Exception.class)
    public ServiceResult createTable(TableInfo tableInfo) {

        StringBuilder createSb = new StringBuilder("create table " + tableInfo.getTableName() + " (");

        try {
            assembleMapper.executeSql("use " + tableInfo.getDatabaseName() + ";");
            //构造列
            for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
                createSb.append(columnInfo.getColumnName()).append(" ").append(columnInfo.getColumnType()).append(" ");
                //默认值
                if (!StrUtil.isEmpty(columnInfo.getDefaultValue())) {
                    //添加单引号
                    String defaultValue = StrUtils.addSingleQuotation(columnInfo.getDefaultValue());
                    createSb.append(" default ").append(defaultValue).append(" ");
                }
                //判断是否自增
                if (columnInfo.getIsAutoInc() != null && columnInfo.getIsAutoInc()) {
                    createSb.append(" auto_increment ");
                } else {
                    //判断是否为空
                    if (columnInfo.getIsNull() != null && columnInfo.getIsNull()) {
                        createSb.append(" null ");
                    } else {
                        createSb.append(" not null ");
                    }
                }
                //注释
                if (!StrUtil.isEmpty(columnInfo.getColumnComment())) {
                    //添加单引号
                    String comment = StrUtils.addSingleQuotation(columnInfo.getColumnComment());
                    createSb.append(" comment ").append(comment);
                }
                createSb.append(",");
            }
            createSb.deleteCharAt(createSb.length() - 1);
            createSb.append(")");

            //注释
            if (!StrUtil.isEmpty(tableInfo.getComment())) {
                //添加单引号
                String comment = StrUtils.addSingleQuotation(tableInfo.getComment());
                createSb.append(" comment ").append(comment).append(";");
            } else {
                createSb.append(";");
            }


            //约束
            for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
                if (columnInfo.getIsPk() != null && columnInfo.getIsPk()) {
                    String sql = "alter table " + tableInfo.getTableName() + " " +
                            "add constraint " + tableInfo.getTableName() + "_pk primary key"
                            + "(" + columnInfo.getColumnName() + ");";
                    log.info(sql);
                    createSb.append(sql);
//                    assembleMapper.createTable(sql);
                }
                if (columnInfo.getIsUnique() != null && columnInfo.getIsUnique()) {
                    String sql = "create unique index " + tableInfo.getTableName() + "_" +
                            columnInfo.getColumnName() + "_uindex on " + tableInfo.getTableName() +
                            " " + "(" + columnInfo.getColumnName() + ");";
                    log.info(sql);
//                    assembleMapper.createTable(sql);
                    createSb.append(sql);
                }
            }
            String createSql = createSb.toString();
            log.info(createSql);
            assembleMapper.executeSql(createSql);
            assembleMapper.executeSql("use meta_sql;");
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            try {
                assembleMapper.executeSql("use meta_sql;");
            } catch (SQLSyntaxErrorException ex) {
                ex.printStackTrace();
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult dropTable(String tableName, String databaseName) {

        String sql = "drop table " + tableName + ";";
        log.info(sql);
        try {
            assembleMapper.executeSql("use " + databaseName + ";");
            assembleMapper.executeSql(sql);
            assembleMapper.executeSql("use meta_sql;");
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }

    }

    public ServiceResult updateTableInfo(ModifyTableInfo modifyTableInfo) {

        try {
            assembleMapper.executeSql("use " + modifyTableInfo.getDatabaseName() + ";");
            String str = "rename table" + modifyTableInfo.getOldTableName() + " to " + modifyTableInfo.getNewName() + ";";
            log.info(str);
            assembleMapper.executeSql(str);
            //添加单引号
            String comment = StrUtils.addSingleQuotation(modifyTableInfo.getComment());
            str = "alter table" + modifyTableInfo.getNewName() + " comment " + comment + ";";
            log.info(str);
            assembleMapper.executeSql(str);
            assembleMapper.executeSql("use meta_sql;");
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            return new ServiceResult(false, e.getCause().toString());
        }
    }

    @Transactional
    public ServiceResult addTableField(TableInfo tableInfo) {

        try {
            assembleMapper.executeSql("use " + tableInfo.getDatabaseName() + ";");
            for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
                log.info("开始为表" + tableInfo.getTableName() + "增加字段" + columnInfo.getColumnName());
                assembleMapper.addTableField(tableInfo.getTableName(), columnInfo);
                log.info("完成为表" + tableInfo.getTableName() + "增加字段" + columnInfo.getColumnName());
            }

            assembleMapper.executeSql("use meta_sql;");
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }
    }

    @Transactional
    public ServiceResult deleteTableField(TableInfo tableInfo) {
        try {
            assembleMapper.executeSql("use " + tableInfo.getDatabaseName() + ";");
            for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
                log.info("开始为表" + tableInfo.getTableName() + "删除字段" + columnInfo.getColumnName());
                assembleMapper.deleteTableField(tableInfo.getTableName(), columnInfo);
                log.info("完成为表" + tableInfo.getTableName() + "删除字段" + columnInfo.getColumnName());
            }

            assembleMapper.executeSql("use meta_sql;");
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }
    }

    public ServiceResult modifyTableField(ModifyTableInfo modifyTableInfo) {

        try {
            assembleMapper.executeSql("use " + modifyTableInfo.getDatabaseName() + ";");
            for (ModifyColumnInfo modifyColumnInfo : modifyTableInfo.getModifyColumnInfoList()) {
                log.info("开始为表" + modifyTableInfo.getNewName() + "修改字段" + modifyColumnInfo.getOldColumnName());
                assembleMapper.modifyTableField(modifyTableInfo.getNewName(), modifyColumnInfo);
                log.info("完成为表" + modifyTableInfo.getNewName() + "修改字段" + modifyColumnInfo.getOldColumnName());
            }

            assembleMapper.executeSql("use meta_sql;");
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }
    }
}
