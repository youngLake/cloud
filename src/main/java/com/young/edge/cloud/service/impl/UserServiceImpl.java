package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.commen.utils.EntityUtil;
import com.young.edge.cloud.commen.utils.ExampleUtil;
import com.young.edge.cloud.commen.utils.MD5Utils;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.UserDao;
import com.young.edge.cloud.domain.User;
import com.young.edge.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
        example.setLoginName(user.getLoginName());
        example.setStatus(1);
        Optional<User> one = userDao.findOne(Example.of(example));
        if (one.isPresent()){
            User result = one.get();
            if (MD5Utils.isEqual(result.getPassword(),user.getPassword())){
                result.setLoginTime(new Date());
                userDao.save(result);
                return "userId="+result.getId();
            }
        }
        return "redirect:/login";
    }

    @Override
    public String getUserNameById(String userId) {
        User user = ExampleUtil.setSpecificField(new User().getClass(), "id", userId);
        if (null!=user){
            Optional<User> one = userDao.findOne(Example.of(user));
            if (one.isPresent()){
                return one.get().getUsername();
            }
        }
        return "";
    }

    @Override
    public String getMyRole(String userId) {
        User user = ExampleUtil.setSpecificField(new User().getClass(), "id", userId);
        if (null!=user){
            Optional<User> one = userDao.findOne(Example.of(user));
            if (one.isPresent()){
                return one.get().getRole();
            }
        }
        return "";
    }

    @Override
    public PageResult userList(PageParameters parameters) {
        PageRequest request=PageRequest.of(parameters.getIndex(),parameters.getSize());
        PageResult result=new PageResult();
        Page<User> all;
        if (ObjectUtils.isEmpty(parameters.getName())){
            all=userDao.getAllNormalStatus(request);
        }else {
            all=userDao.vaguelyGetAllNormalStatus(request, parameters.getName());
        }
        result.setRows(all.getContent());
        result.setTotal(all.getTotalElements());
        return result;
    }

    @Override
    public int addNewUser(User user) {
        if (ObjectUtils.isEmpty(user.getId())){
            user.setId(UUID.randomUUID().toString());
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            user.setRole("2");
            user.setStatus(1);
            user.setCreateTime(new Date());
        }else {
            User example=new User();
            example.setId(user.getId());
            Optional<User> one = userDao.findOne(Example.of(example));
            if (one.isPresent()){
                User previous = one.get();
                EntityUtil.setOldValueForNullField(user,previous,"id","status","createTime","role","roleDesc");
            }
        }
        userDao.save(user);
        return 1;
    }

    @Override
    public User getMyProfile(String userId) {
        Optional<User> user = userDao.findById(userId);
        return user.isPresent()?user.get():null;
    }

    @Override
    public int updateProfile(User user) {
        User example=new User();
        example.setId(user.getId());
        Optional<User> one = userDao.findOne(Example.of(example));
        if (one.isPresent()){
            User previous = one.get();
            if (!ObjectUtils.isEmpty(user.getPassword()) && !"".equals(user.getPassword())){
                user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
                EntityUtil.setOldValueForNullField(user,previous,"createTime","loginTime","status","role","roleDesc");
            }else {
                EntityUtil.setOldValueForNullField(user,previous,"createTime","loginTime","status","role","roleDesc","password");
            }
            userDao.save(user);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteUserById(String id) {
        User example=new User();
        example.setId(id);
        Optional<User> one = userDao.findOne(Example.of(example));
        if (one.isPresent()){
            User user = one.get();
            user.setStatus(2);
            userDao.save(user);
            return 1;
        }
        return 0;
    }
}
