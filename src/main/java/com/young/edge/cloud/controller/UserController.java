package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.utils.JwtUtils;
import com.young.edge.cloud.domain.User;
import com.young.edge.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Tornado Young
 * @date time 2020/3/2 22:57
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(HttpServletResponse response, HttpServletRequest request){
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user=new User();
            user.setLoginName(username);
            user.setPassword(password);
            if (userService.loginValidation(user).equals("index")){
                request.getSession().setAttribute("token", JwtUtils.createJWT(username, UUID.randomUUID().toString(),1000*60*60*24L));
                return "index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}
