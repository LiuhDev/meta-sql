package com.hlxd.metasql.utils;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/29
 */
public class StrUtils {

    /**
     * add single quotation to a string
     * @param str input string
     * @return result string
     */
    public static String addSingleQuotation(String str) {
        return "'" + str + "'";
    }

}
