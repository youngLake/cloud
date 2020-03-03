package com.young.edge.cloud.service;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.WorkOrder;

/**
 * @author Tornodo Young
 * @date 2020/3/3 14:56
 */
public interface WorkOrderService {
    PageResult workOrderList(PageParameters parameters);
    int addNewWorkOrder(WorkOrder workOrder);
}
