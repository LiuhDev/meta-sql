package com.hlxd.metasql.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：liuhao
 * @date ：Created in 2021/7/29
 */
@Data
@AllArgsConstructor
public class ServiceResult {

    private boolean success;

    private String info;
}
