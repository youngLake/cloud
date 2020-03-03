package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.controller.vo.BasicCountInfoVo;
import com.young.edge.cloud.dao.OrderDao;
import com.young.edge.cloud.dao.ProjectDao;
import com.young.edge.cloud.dao.UserDao;
import com.young.edge.cloud.service.BaseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        vo.setUserNumber(userDao.countNormalStatus());
        vo.setOrderNumber(orderDao.countNumberNormalStatus());
        vo.setOrderAmount(orderDao.countAmountNormalStatus());
        vo.setProjectNumber(projectDao.countNormalStatus());
        return vo;
    }
}
