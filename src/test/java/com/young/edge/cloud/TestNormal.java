package com.young.edge.cloud;

import com.young.edge.cloud.mt.MultiThreadTest1;
import com.young.edge.cloud.mt.MultiThreadTest2;
import com.young.edge.cloud.mt.MultiThreadTestInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Tornado Young
 * @date time 2020/3/2 23:28
 */
public class TestNormal {
    public static void main(String[] args) {
        List<MultiThreadTestInterface> list=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            if (i%2==0){
                list.add(new MultiThreadTest1(i));
            }else {
                list.add(new MultiThreadTest2(i));
            }
        }
        //list.forEach(MultiThreadTestInterface::run);
        System.err.println("=======================");
        BlockingQueue<Runnable> queue=new ArrayBlockingQueue<>(list.size(),true);
        ThreadPoolExecutor executor= new ThreadPoolExecutor(10,20,3, TimeUnit.SECONDS,queue);
        executor.prestartAllCoreThreads();
        list.forEach(executor::execute);
        executor.shutdown();
    }
}
