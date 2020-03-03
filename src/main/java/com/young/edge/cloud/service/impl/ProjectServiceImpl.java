package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.ProjectDao;
import com.young.edge.cloud.domain.Project;
import com.young.edge.cloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tornodo Young
 * @date 2020/3/3 13:58
 */
@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectDao projectDao;
    @Override
    public PageResult projectList(PageParameters parameters) {
        PageRequest request=PageRequest.of(parameters.getIndex(),parameters.getSize());
        PageResult result=new PageResult();
        Page<Project> all;
        if (ObjectUtils.isEmpty(parameters.getName())){
            all=projectDao.getAllNormalStatus(request);
        }else {
            all=projectDao.vaguelyGetAllNormalStatus(request, parameters.getName());
        }
        result.setRows(all.getContent());
        result.setTotal(all.getTotalPages());
        return result;
    }

    @Override
    public List<Map<String,String>> getProjectMap() {
        List<Map<String,String>> list=new ArrayList<>();
        projectDao.findAll()
                .stream()
                .filter(p -> p.getStatus()==1)
                .forEach(p -> {
                    Map<String,String> map=new HashMap<>();
                    map.put("id",p.getId());
                    map.put("name",p.getName());
                    list.add(map);
                });
        return list;
    }
}
