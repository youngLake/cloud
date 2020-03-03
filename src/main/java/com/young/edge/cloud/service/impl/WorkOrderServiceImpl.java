package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.WorkOrderDao;
import com.young.edge.cloud.domain.WorkOrder;
import com.young.edge.cloud.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
            all=workOrderDao.findAll(request);
        }else {
            all=workOrderDao.vaguelyGetAllNormalStatus(request, parameters.getName());
        }
        result.setRows(all.getContent());
        result.setTotal(all.getTotalPages());
        return result;
    }
}
