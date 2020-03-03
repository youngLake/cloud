package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.constant.RSP;
import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.WorkOrder;
import com.young.edge.cloud.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tornodo Young
 * @date 2020/3/3 14:54
 */
@RestController
public class WorkOrderController extends ParentContrller{

    @Autowired
    WorkOrderService workOrderService;
    @RequestMapping(value = "/workOrderList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageResult workOrderList(@RequestBody PageParameters parameters){
        try {
            return workOrderService.workOrderList(parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @RequestMapping(value = "/addNewWorkOrder",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RSP addNewWorkOrder(HttpServletRequest request, @RequestBody WorkOrder workOrder){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId)){
                workOrder.setUserId(userId.toString());
            }
            return ok(workOrderService.addNewWorkOrder(workOrder)+"");
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }
}
