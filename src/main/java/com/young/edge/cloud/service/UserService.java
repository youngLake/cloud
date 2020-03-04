package com.young.edge.cloud.service;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.User;

public interface UserService {
    /**
     * 登录校验
     * @param user
     * @return
     */
    String loginValidation(User user);

    String getUserNameById(String userId);

    String getMyRole(String userId);

    PageResult userList(PageParameters parameters);

    int addNewUser(User user);
}
