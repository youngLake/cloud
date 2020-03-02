package com.young.edge.cloud;

import com.young.edge.cloud.dao.OrderDao;
import com.young.edge.cloud.dao.ProjectDao;
import com.young.edge.cloud.dao.UserDao;
import com.young.edge.cloud.dao.WorkOrderDao;
import com.young.edge.cloud.domain.Order;
import com.young.edge.cloud.domain.Project;
import com.young.edge.cloud.domain.User;
import com.young.edge.cloud.domain.WorkOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CloudApplicationTests {

    @Autowired
    OrderDao orderDao;
    @Autowired
    ProjectDao projectDao;
    @Autowired
    UserDao userDao;
    @Autowired
    WorkOrderDao workOrderDao;
	@Test
	void contextLoads() {
        Order order=new Order();
        order.setId(UUID.randomUUID().toString());
        orderDao.save(order);
        Project project=new Project();
        project.setId(order.getId());
        projectDao.save(project);
        User user=new User();
        user.setId(order.getId());
        userDao.save(user);
        WorkOrder workOrder=new WorkOrder();
        workOrder.setId(order.getId());
        workOrderDao.save(workOrder);
	}

}
