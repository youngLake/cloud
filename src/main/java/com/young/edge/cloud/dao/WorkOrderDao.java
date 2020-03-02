package com.young.edge.cloud.dao;

import com.young.edge.cloud.domain.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:21
 */
public interface WorkOrderDao extends JpaRepository<WorkOrder,String> {
}
