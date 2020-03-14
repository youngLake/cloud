package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.commen.utils.EntityUtil;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.OrderDao;
import com.young.edge.cloud.domain.Order;
import com.young.edge.cloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Tornodo Young
 * @date 2020/3/3 12:17
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public PageResult orderList(PageParameters parameters) {
        PageRequest request= PageRequest.of(parameters.getIndex(),parameters.getSize());
        Page<Order> all;
        if (ObjectUtils.isEmpty(parameters.getName())){
            all=orderDao.getAllNormalStatus(request);
        }else {
            all=orderDao.vaguelyGetAllNormalStatusByPhone(request,parameters.getName());
        }
        PageResult result=new PageResult();
        result.setTotal(all.getTotalElements());
        result.setRows(all.getContent());
        return result;
    }

    @Override
    public int addNewOrder(Order order) {
        if (ObjectUtils.isEmpty(order.getId())) {
            order.setId(UUID.randomUUID().toString());
            order.setGender(SystemConstant.genderMap.get(order.getGender()));
            order.setStatus(1);
            order.setCreateTime(new Date());
        }else {
            Order example=new Order();
            example.setId(order.getId());
            Optional<Order> one = orderDao.findOne(Example.of(example));
            if (one.isPresent()){
                Order previous = one.get();
                EntityUtil.setOldValueForNullField(order,previous,"id","status","createTime","userId");
            }
        }
        orderDao.save(order);
        return 1;
    }

    @Override
    public Object orderAnalysis() {
        return SystemConstant.orderAnalysis;
    }

    @Override
    public int deleteOrderById(String id) {
        Order example=new Order();
        example.setId(id);
        Optional<Order> one = orderDao.findOne(Example.of(example));
        if (one.isPresent()){
            Order order = one.get();
            order.setStatus(2);
            orderDao.save(order);
            return 1;
        }
        return 0;
    }
}
