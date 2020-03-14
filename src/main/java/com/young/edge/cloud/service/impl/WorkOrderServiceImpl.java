package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.commen.utils.EntityUtil;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.WorkOrderDao;
import com.young.edge.cloud.domain.WorkOrder;
import com.young.edge.cloud.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Tornodo Young
 * @date 2020/3/3 14:56
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService {
    @Autowired
    WorkOrderDao workOrderDao;
    @Override
    public PageResult workOrderList(PageParameters parameters) {
        PageRequest request=PageRequest.of(parameters.getIndex(),parameters.getSize());
        PageResult result=new PageResult();
        Page<WorkOrder> all;
        if (ObjectUtils.isEmpty(parameters.getName())){
            all=workOrderDao.getAllNormalStatus(request);
        }else {
            all=workOrderDao.vaguelyGetAllNormalStatus(request, parameters.getName());
        }
        result.setRows(all.getContent());
        result.setTotal(all.getTotalElements());
        return result;
    }

    @Override
    public int addNewWorkOrder(WorkOrder workOrder) {
        if (ObjectUtils.isEmpty(workOrder.getId())) {
            workOrder.setId(UUID.randomUUID().toString());
            workOrder.setStatus(1);
        }else {
            WorkOrder example=new WorkOrder();
            example.setId(workOrder.getId());
            Optional<WorkOrder> one = workOrderDao.findOne(Example.of(example));
            if (one.isPresent()){
                WorkOrder previous = one.get();
                EntityUtil.setOldValueForNullField(workOrder,previous,"id","status","userId");
            }
        }
        workOrderDao.save(workOrder);
        return 1;
    }

    @Override
    public int deleteWorkOrderById(String id) {
        WorkOrder example=new WorkOrder();
        example.setId(id);
        Optional<WorkOrder> one = workOrderDao.findOne(Example.of(example));
        if (one.isPresent()){
            WorkOrder workOrder = one.get();
            workOrder.setStatus(2);
            workOrderDao.save(workOrder);
            return 1;
        }
        return 0;
    }
}
