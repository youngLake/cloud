package com.young.edge.cloud.controller.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Tornodo Young
 * @date 2020/3/3 12:36
 */
@Getter
@Setter
public class PageResult {
    private Integer total;
    private List rows;
}
