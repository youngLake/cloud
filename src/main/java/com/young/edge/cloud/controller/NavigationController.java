package com.young.edge.cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Tornodo Young
 * @date 2020/3/2 17:02
 */
@Controller
public class NavigationController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/order")
    public String order(){
        return "order";
    }
    @RequestMapping("/project")
    public String project(){
        return "project";
    }
    @RequestMapping("/user")
    public String user(){
        return "user";
    }
    @RequestMapping("/workorder")
    public String workorder(){
        return "workorder";
    }
}
