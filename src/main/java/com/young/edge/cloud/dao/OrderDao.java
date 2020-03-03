package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:19
 */
public interface OrderDao extends JpaRepository<Order,String> {
    @Query(value = "SELECT COUNT(0) FROM orders WHERE `status`=1",nativeQuery = true)
    Integer countNumberNormalStatus();

    @Query(value = "SELECT SUM(amout) FROM orders WHERE `status`=1",nativeQuery = true)
    BigDecimal countAmountNormalStatus();

    @Query(value = "SELECT * FROM orders WHERE `status`=1",nativeQuery = true)
    Page<Order> getAllNormalStatus(Pageable pageable);

    @Query(value = "SELECT * FROM orders WHERE `status`=1 AND phone LIKE CONCAT('%',:phone,'%')",nativeQuery = true)
    Page<Order> vaguelyGetAllNormalStatusByPhone(Pageable pageable,@Param("phone")String phone);
}
