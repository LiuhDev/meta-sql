package com.hlxd.metasql.service;

import cn.hutool.core.util.StrUtil;
import com.hlxd.metasql.entity.ColumnInfo;
import com.hlxd.metasql.entity.TableInfo;
import com.hlxd.metasql.mapper.AssembleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Vector;

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

    public boolean createTable(TableInfo tableInfo, String databaseName) {

        StringBuilder createSb = new StringBuilder("create table " + tableInfo.getTableName() + " (");

        //构造列
        for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
            createSb.append(columnInfo.getColumnName()).append(" ").append(columnInfo.getColumnType()).append(" ");
            //默认值
            if (!StrUtil.isEmpty(columnInfo.getDefaultValue())) {
                createSb.append(" default ").append(columnInfo.getDefaultValue()).append(" ");
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
                createSb.append(" comment ").append(columnInfo.getColumnComment());
            }
            createSb.append(",");
        }
        createSb.deleteCharAt(createSb.length() - 1);
        createSb.append(");");
        //注释
        if (!StrUtil.isEmpty(tableInfo.getComment())) {
            createSb.append(" comment ").append(tableInfo.getComment());
        }
        String createSql = createSb.toString();
        log.info(createSql);
        assembleMapper.createTable(createSql);

        //约束
        for (ColumnInfo columnInfo : tableInfo.getColumnList()) {
            if (columnInfo.getIsPk() != null && columnInfo.getIsPk()) {
                String sql = "alter table " + tableInfo.getTableName() + " " +
                        "add constraint " + tableInfo.getTableName() + "_pk" +
                        "primary key" + "(" + columnInfo.getColumnName() + ");";
            }
            if (columnInfo.getIsUnique() != null && columnInfo.getIsUnique()) {
                String sql = "create unique index " + tableInfo.getTableName() + "_" +
                        columnInfo.getColumnName() + "_uindex on " + tableInfo.getTableName() +
                        " " + "(" + columnInfo.getColumnName() + ");";
            }
        }
        return true;
    }
}
