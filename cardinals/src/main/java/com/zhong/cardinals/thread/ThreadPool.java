package com.zhong.cardinals.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhong on 2017/3/23.
 * 线程池
 */

public class ThreadPool {
    private static ExecutorService cachedThreadPool;

    private ThreadPool() {
    }

    public static ExecutorService getCachedThreadPool() {

        if (cachedThreadPool == null) {
            cachedThreadPool = Executors.newCachedThreadPool();
        }
        return cachedThreadPool;
    }
}
