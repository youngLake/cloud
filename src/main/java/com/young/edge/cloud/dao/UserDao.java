package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.Project;
import com.young.edge.cloud.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:20
 */
public interface UserDao extends JpaRepository<User,String> {
    @Query(value = "SELECT COUNT(0) FROM `user` WHERE `status`=1",nativeQuery = true)
    Integer countNormalStatus();

    @Query(value = "SELECT * FROM `user` WHERE `status`=1",nativeQuery = true)
    Page<User> getAllNormalStatus(Pageable pageable);

    @Query(value = "SELECT * FROM `user` WHERE `status`=1 AND `login_name` LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    Page<User> vaguelyGetAllNormalStatus(Pageable pageable, @Param("name")String name);
}
