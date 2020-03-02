package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:20
 */
public interface UserDao extends JpaRepository<User,String> {
}
