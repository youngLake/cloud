package com.young.edge.cloud.commen.config;

import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.commen.utils.JwtUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tornado Young
 * @date time 2020/3/2 21:27
 */
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.err.println(request);
//        if (handler instanceof HandlerMethod){
//            String authHeader = request.getHeader(SystemConstant.TOKEN);
//            if (!ObjectUtils.isEmpty(authHeader)){
//                CheckResult checkResult = JwtUtils.validateJWT(authHeader);
//                if (checkResult.isSuccess()){
//                    return true;
//                }
//            }
//        }
//        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        //从session中获取标识 toekn
        String token = (String) request.getSession().getAttribute(SystemConstant.TOKEN);
        //如果token存在，通过用户请求，返回 true
        if (!ObjectUtils.isEmpty(token) && JwtUtils.validateJWT(token).isSuccess()){
            return true;
        }
        //如果token不存在，则重定向到登录界面
        response.sendRedirect("/login");
        return false;
    }
}
