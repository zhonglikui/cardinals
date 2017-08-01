package com.zhong.cardinals.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by zhong on 2017/3/23.
 * 线程池
 */

public class ThreadPool {
    private static ExecutorService cachedThreadPool;
    private static ScheduledExecutorService scheduler;

    private ThreadPool() {
    }

    public static ExecutorService getCachedThreadPool() {

        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        return cachedThreadPool;
    }

   /* public static ScheduledExecutorService getScheduledThreadPool() {

        if (scheduler == null) {
            scheduler = Executors.newScheduledThreadPool(1);
        }
        return scheduler;
    }*/
}
