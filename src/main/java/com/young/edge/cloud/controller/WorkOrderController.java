package com.young.edge.cloud.controller;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
