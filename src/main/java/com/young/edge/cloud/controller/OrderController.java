package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.constant.RSP;
import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.Order;
import com.young.edge.cloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tornodo Young
 * @date 2020/3/3 12:09
 */
@RestController
public class OrderController extends ParentContrller{

    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "/orderList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageResult orderList(@RequestBody PageParameters parameters){
        try {
            return orderService.orderList(parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @RequestMapping(value = "/addNewOrder",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RSP addNewOrder(@RequestBody Order order, HttpServletRequest request){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId)){
                order.setUserId(userId.toString());
            }
            orderService.addNewOrder(order);
            return ok("");
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }

    @RequestMapping(value = "/orderAnalysis",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RSP orderAnalysis(){
        try {
            return ok(orderService.orderAnalysis());
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }

    @RequestMapping(value = "/deleteOrderById",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RSP deleteOrderById(@RequestParam("id")String id){
        try {
            return ok(orderService.deleteOrderById(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }
}
