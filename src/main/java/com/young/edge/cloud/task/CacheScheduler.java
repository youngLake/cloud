package com.young.edge.cloud.task;

import com.young.edge.cloud.commen.constant.SystemConstant;
import com.young.edge.cloud.dao.OrderDao;
import com.young.edge.cloud.dao.ProjectDao;
import com.young.edge.cloud.dao.WorkOrderDao;
import com.young.edge.cloud.domain.Order;
import com.young.edge.cloud.domain.Project;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 首页展示数据缓存
 * @author Tornado Young
 * @date time 2020/3/4 21:10
 */
@EnableAsync
@Component
@Log4j2
public class CacheScheduler {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private WorkOrderDao workOrderDao;
    @Autowired
    private ProjectDao projectDao;
    @Async
    @Scheduled(fixedRate = 1000*60 ,initialDelay = 1000L)
    public void cache(){
        try {
            log.debug("{} is working.......",this.getClass().getCanonicalName());
            List<Order> validOrderList = orderDao.findAll()
                    .stream()
                    .filter(order -> order.getStatus() == 1)
                    .collect(Collectors.toList());
            BigDecimal totalAmount=validOrderList.stream()
                    .filter(order -> !ObjectUtils.isEmpty(order.getAmout()))
                    .map(Order::getAmout)
                    .reduce(new BigDecimal("0.00"),BigDecimal::add);
            SystemConstant.orderAnalysis.put("totalAmount",totalAmount);
            List top4Projects=new ArrayList();
            Map<String, List<Order>> projectMap = validOrderList.stream()
                    .filter(order -> !ObjectUtils.isEmpty(order.getProjectId()))
                    .collect(Collectors.groupingBy(Order::getProjectId));
            Project projectExample=new Project();
            projectMap.keySet().stream()
                    .sorted(Comparator.comparing(s -> projectMap.get(s).size()).reversed())
                    .limit(4L)
                    .collect(Collectors.toList())
                    .forEach(s -> {
                        projectExample.setId(s);
                        Optional<Project> one = projectDao.findOne(Example.of(projectExample));
                        if (one.isPresent()){
                            Map<String,String> map=new HashMap<>();
                            map.put("projectName",one.get().getName());
                            map.put("projectCount",projectMap.get(s).size()+"");
                            top4Projects.add(map);
                        }
                    });
            SystemConstant.orderAnalysis.put("top4",top4Projects);
            int customers=validOrderList.stream()
                    .filter(order -> !ObjectUtils.isEmpty(order.getPhone()))
                    .collect(Collectors.groupingBy(Order::getPhone))
                    .size();
            SystemConstant.orderAnalysis.put("customers",customers);
            int workOrders = workOrderDao.findAll()
                    .stream()
                    .filter(workOrder -> workOrder.getStatus() == 1)
                    .collect(Collectors.toList())
                    .size();
            SystemConstant.orderAnalysis.put("workOrders",workOrders);
            List userStatics=new ArrayList();
            Map<String, List<Order>> userStaticsMap = validOrderList.stream()
                    .filter(order -> !ObjectUtils.isEmpty(order.getGender()))
                    .collect(Collectors.groupingBy(Order::getGender));
            long total=userStaticsMap.keySet().stream().collect(Collectors.summarizingInt(s -> userStaticsMap.get(s).size())).getSum();
            userStaticsMap.keySet()
                    .stream()
                    .forEach(s -> {
                        int size = userStaticsMap.get(s).size();
                        BigDecimal oneHundred = new BigDecimal("100.00");
                        BigDecimal ratio = oneHundred.multiply(new BigDecimal("" + size)).divide(new BigDecimal(total),2);
                        if (s.equals("1")){
                            userStatics.add("男-"+ratio);
                        }else if (s.equals("2")){
                            userStatics.add("女-"+ratio);
                        }else if (s.equals("3")){
                            userStatics.add("未知-"+ratio);
                        }
                    });
            SystemConstant.orderAnalysis.put("userStatics",userStatics);
            Calendar calendar = Calendar.getInstance();
            Map<String,Integer> months=new HashMap<>();
            int thisYear = calendar.get(Calendar.YEAR);
            for (int i = 0; i <12 ; i++) {
                int month=i+1;
                months.put(thisYear+"-"+(month<10?"0"+month:month),0);
            }
            validOrderList.stream()
                    .filter(order -> !ObjectUtils.isEmpty(order.getCreateTime()))
                    .forEach(order -> {
                        calendar.setTime(order.getCreateTime());
                        int year = calendar.get(Calendar.YEAR);
                        if (year==thisYear){
                            int month = calendar.get(Calendar.MONTH)+ 1;
                            String key=year+"-"+(month<10?"0"+month:month);
                            months.replace(key,months.get(key)+1);
                        }
                    });
            List<String> monthlyData=new ArrayList();
            for (String s : months.keySet()) {
                monthlyData.add(s+"==="+months.get(s));
            }
            monthlyData=monthlyData.stream().sorted().collect(Collectors.toList());
            SystemConstant.orderAnalysis.put("months",monthlyData);
            log.debug("{} is done.......",this.getClass().getCanonicalName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
