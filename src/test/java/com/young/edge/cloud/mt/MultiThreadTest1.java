package com.young.edge.cloud.mt;

/**
 * @author Tornodo Young
 * @date 2020/4/16 17:20
 */
public class MultiThreadTest1 implements MultiThreadTestInterface {
    private int i;
    public MultiThreadTest1(int i){
        this.i=i;
    }

    @Override
    public void init() {
        System.out.println(getClass().getSimpleName()+"-"+i+" has been initiated.");
    }

    @Override
    public void destroy() {
        System.out.println(getClass().getSimpleName()+"-"+i+" has been destroyed.");
    }

    @Override
    public void dowork() {
        System.out.println(getClass().getSimpleName()+"-"+i+" is doing work.");
    }

    @Override
    public void run() {
        init();
        dowork();
        destroy();
    }
}
