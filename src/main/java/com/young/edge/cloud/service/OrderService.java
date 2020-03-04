package com.young.edge.cloud.service;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.Order;

/**
 * @author Tornodo Young
 * @date 2020/3/3 12:14
 */
public interface OrderService {
    PageResult orderList(PageParameters parameters);
    int addNewOrder(Order order);
    Object orderAnalysis();
}
