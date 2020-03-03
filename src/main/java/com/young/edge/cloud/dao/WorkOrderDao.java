package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.WorkOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:21
 */
public interface WorkOrderDao extends JpaRepository<WorkOrder,String> {
    @Query(value = "SELECT * FROM workorder WHERE `status`=1 AND `title` LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    Page<WorkOrder> vaguelyGetAllNormalStatus(Pageable pageable, @Param("name")String name);

    @Query(value = "SELECT * FROM workorder WHERE `status`=1",nativeQuery = true)
    Page<WorkOrder> getAllNormalStatus(Pageable pageable);
}
