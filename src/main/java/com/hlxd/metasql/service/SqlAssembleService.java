package com.hlxd.metasql.service;

import cn.hutool.core.util.StrUtil;
import com.hlxd.metasql.common.ServiceResult;
import com.hlxd.metasql.entity.ColumnInfo;
import com.hlxd.metasql.entity.TableInfo;
import com.hlxd.metasql.mapper.AssembleMapper;
import com.hlxd.metasql.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Map;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/15
 */
@Service
@Slf4j
public class SqlAssembleService {

    @Resource
    private AssembleMapper assembleMapper;

    /**
     * 动态生成SQ及SQL参数L
     *
     * @return
     */
//    public String updateSqlAndParamList(Vector<String> ve, List<String> paramList, String table, List<String> list) {
//        StringBuilder strSql = new StringBuilder();//MQ消息SQl
//        StringBuilder upSql = new StringBuilder();//可执行SQL
//        try {
//            //组装SQL语句
//            strSql = new StringBuilder("update " + table + " set ");
//            upSql = new StringBuilder("update " + table + " set ");
//            for (int i = 0; i < ve.size(); i++) {
//                String str;
//                String upStr;
//                String key = ve.get(i);
//                String fileName = "get" + key.toUpperCase();
//                String value = (String) t.getClass().getMethod(fileName).invoke(t);
//                paramList.add(i, value);
//                if (i == ve.size() - 1) {
//                    str = key + " = ?";
//                    upStr = key + "='" + value + "'";
//                } else {
//                    str = key + " = ? ,";
//                    upStr = key + "='" + value + "',";
//                }
//                strSql.append(str);
//                upSql.append(upStr);
//            }
//            strSql.append(" where Id = ? ");
//            upSql.append(" where id='").append((String) t.getClass().getMethod("getID").invoke(t)).append("'");
//            list.add(upSql.toString());
////            paramList.add(ve.size(), (String) t.getClass().getMethod("getID").invoke(t));
//        } catch (Exception e) {
//            log.info("组装UPDATE SQL失败！失败详情---" + e);
//
//        }
//        return strSql.toString();
//    }

    @Transactional(rollbackFor = Exception.class)
    public ServiceResult createTable(TableInfo tableInfo, String databaseName) {

        StringBuilder createSb = new StringBuilder("create table " + tableInfo.getTableName() + " (");

        try {
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
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }

    }

    public ServiceResult dropTable(String tableName, String databaseName) {

        String sql = "drop table " + tableName + ";";
        log.info(sql);
        try {
            assembleMapper.executeSql(sql);
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ServiceResult(false, e.getCause().toString());
        }

    }

    public ServiceResult updateTable(TableInfo tableInfo, String databaseName) {
        String sql = "use test;";
        String sql2 = "select * from table_name;";
        try {
            assembleMapper.executeSql(sql);
            Map<String, String> map = assembleMapper.querySql(sql2);
            assembleMapper.executeSql("use meta_sql");
            System.out.println(map);
            return new ServiceResult(true, null);
        } catch (Exception e) {
            log.error(e.getCause().toString());
            return new ServiceResult(false, e.getCause().toString());
        }
    }

}
