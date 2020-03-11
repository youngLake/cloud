package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.controller.vo.BasicCountInfoVo;
import com.young.edge.cloud.dao.OrderDao;
import com.young.edge.cloud.dao.ProjectDao;
import com.young.edge.cloud.dao.UserDao;
import com.young.edge.cloud.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Tornodo Young
 * @date 2020/3/3 11:36
 */
@Service
public class BaseInfoServiceImpl implements BaseInfoService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public BasicCountInfoVo basicCount() {
        BasicCountInfoVo vo=new BasicCountInfoVo();
        Integer countNormalStatus = userDao.countNormalStatus();
        vo.setUserNumber(countNormalStatus==null?0:countNormalStatus);
        Integer countNumberNormalStatus = orderDao.countNumberNormalStatus();
        vo.setOrderNumber(countNumberNormalStatus==null?0:countNumberNormalStatus);
        BigDecimal countAmountNormalStatus = orderDao.countAmountNormalStatus();
        vo.setOrderAmount(countAmountNormalStatus==null?new BigDecimal("0.00"):countAmountNormalStatus);
        Integer countNormalStatus1 = projectDao.countNormalStatus();
        vo.setProjectNumber(countNormalStatus1==null?0:countNormalStatus1);
        return vo;
    }
}
