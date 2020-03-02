package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:19
 */
public interface ProjectDao extends JpaRepository<Project,String> {
}
