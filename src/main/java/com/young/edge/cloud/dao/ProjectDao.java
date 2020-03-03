package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:19
 */
public interface ProjectDao extends JpaRepository<Project,String> {
    @Query(value = "SELECT COUNT(0) FROM project WHERE `status`=1",nativeQuery = true)
    Integer countNormalStatus();

    @Query(value = "SELECT * FROM project WHERE `status`=1",nativeQuery = true)
    Page<Project> getAllNormalStatus(Pageable pageable);

    @Query(value = "SELECT * FROM project WHERE `status`=1 AND `name` LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    Page<Project> vaguelyGetAllNormalStatus(Pageable pageable, @Param("name")String name);
}
