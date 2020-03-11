package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.constant.RSP;
import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.commen.utils.JwtUtils;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.User;
import com.young.edge.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Tornado Young
 * @date time 2020/3/2 22:57
 */
@Controller
public class UserController extends ParentContrller{

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
            String loginValidation = userService.loginValidation(user);
            if (loginValidation.startsWith("userId=")){
                request.getSession().setAttribute(SystemConstant.TOKEN,JwtUtils.createJWT(username, UUID.randomUUID().toString(),1000*60*60*24L));
                request.getSession().setAttribute(SystemConstant.USER_ID,loginValidation.split("=")[1]);
                return "index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/getMyUserName")
    @ResponseBody
    public String getMyUserName(HttpServletRequest request){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId) && userId instanceof String){
                return userService.getUserNameById(userId.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "getMyRole")
    @ResponseBody
    public String getMyRole(HttpServletRequest request){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId) && userId instanceof String){
                return userService.getMyRole(userId.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        try {
            request.getSession().removeAttribute(SystemConstant.TOKEN);
            request.getSession().removeAttribute(SystemConstant.USER_ID);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "login";
    }

    @RequestMapping(value = "/userList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public PageResult userList(@RequestBody PageParameters parameters){
        try {
            return userService.userList(parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @RequestMapping(value = "/addNewUser",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RSP addNewUser(@RequestBody User user,HttpServletRequest request){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId)){
                user.setRoleDesc(userId+"");
            }
            return ok(userService.addNewUser(user));
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }

    @RequestMapping(value = "/getMyProfile",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RSP getMyProfile(HttpServletRequest request){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId)){
                return ok(userService.getMyProfile(userId.toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }

    @RequestMapping(value = "/updateProfile",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public RSP updateProfile(@RequestBody User user){
        try {
//            String userId = request.getParameter("myUserId");
//            String myLoginName = request.getParameter("myLoginName");
//            String myUsername1 = request.getParameter("myUsername1");
//            String myPassword = request.getParameter("myPassword");
//            User user=new User();
//            user.setId(userId);
//            user.setLoginName(myLoginName);
//            user.setUsername(myUsername1);
//            user.setPassword(myPassword);
            return ok(userService.updateProfile(user));
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }
}
