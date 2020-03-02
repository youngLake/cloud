package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.commen.utils.MD5Utils;
import com.young.edge.cloud.dao.UserDao;
import com.young.edge.cloud.domain.User;
import com.young.edge.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tornado Young
 * @date time 2020/3/2 23:03
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public String loginValidation(User user) {
        User example=new User();
        user.setLoginName(user.getLoginName());
        Optional<User> one = userDao.findOne(Example.of(example));
        if (one.isPresent()){
            User result = one.get();
            if (MD5Utils.isEqual(result.getPassword(),user.getPassword())){
                return "index";
            }
        }
        return "redirect:/login";
    }
}
