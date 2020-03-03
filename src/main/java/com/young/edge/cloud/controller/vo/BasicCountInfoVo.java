package com.young.edge.cloud.controller.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Tornodo Young
 * @date 2020/3/3 11:24
 */
@Getter
@Setter
public class BasicCountInfoVo {
    private Integer userNumber;
    private BigDecimal orderAmount;
    private Integer projectNumber;
    private Integer orderNumber;
}
