package com.young.edge.cloud.service.impl;

import com.young.edge.cloud.commen.utils.EntityUtil;
import com.young.edge.cloud.controller.vo.PageParameters;
import com.young.edge.cloud.controller.vo.PageResult;
import com.young.edge.cloud.dao.ProjectDao;
import com.young.edge.cloud.domain.Project;
import com.young.edge.cloud.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

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
        result.setTotal(all.getTotalElements());
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

    @Override
    public int addNewProject(Project project) {
        if (ObjectUtils.isEmpty(project.getId())){
            project.setId(UUID.randomUUID().toString());
            project.setStatus(1);
        }else {
            Project example=new Project();
            example.setId(project.getId());
            Optional<Project> one = projectDao.findOne(Example.of(example));
            if (one.isPresent()){
                Project previous = one.get();
                EntityUtil.setOldValueForNullField(project,previous,"id","status","userId");
            }
        }
        projectDao.save(project);
        return 1;
    }
}
