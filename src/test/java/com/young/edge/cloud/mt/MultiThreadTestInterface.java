package com.young.edge.cloud.mt;

/**
 * @author Tornodo Young
 * @date 2020/4/16 17:18
 */
public interface MultiThreadTestInterface extends Runnable {
    void init();
    void destroy();
    void dowork();
}
