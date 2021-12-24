package com.hlxd.metasql.entity.vo;

import com.hlxd.metasql.entity.MetadataMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MenuTreeVO extends MetadataMenu {

    List<MenuTreeVO> subMenu;

}
