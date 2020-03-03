package com.young.edge.cloud.service;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.domain.Project;

import java.util.List;
import java.util.Map;

/**
 * @author Tornodo Young
 * @date 2020/3/3 13:58
 */
public interface ProjectService {
    PageResult projectList(PageParameters parameters);
    List<Map<String,String>> getProjectMap();
    int addNewProject(Project project);
}
