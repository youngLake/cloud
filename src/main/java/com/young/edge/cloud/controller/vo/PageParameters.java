package com.young.edge.cloud.controller.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tornodo Young
 * @date 2020/3/3 12:12
 */
@Getter
@Setter
public class PageParameters {
    private Integer index;
    private Integer size;
    private String name;
}
