package com.young.edge.cloud.controller;

import com.young.edge.cloud.commen.constant.RSP;
import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.Project;
import com.young.edge.cloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tornodo Young
 * @date 2020/3/3 13:57
 */
@RestController
public class ProjectController extends ParentContrller{

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/projectList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PageResult projectList(@RequestBody PageParameters parameters){
        try {
            return projectService.projectList(parameters);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @RequestMapping(value = "/getProjectMap")
    public RSP getProjectMap(){
        try {
            return ok(projectService.getProjectMap());
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }

    @RequestMapping(value = "/addNewProject",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RSP addNewProject(@RequestBody Project project, HttpServletRequest request){
        try {
            Object userId = request.getSession().getAttribute(SystemConstant.USER_ID);
            if (!ObjectUtils.isEmpty(userId)){
                project.setUserId(userId.toString());
            }
            return ok(projectService.addNewProject(project)+"");
        }catch (Exception e){
            e.printStackTrace();
        }
        return err();
    }
}
