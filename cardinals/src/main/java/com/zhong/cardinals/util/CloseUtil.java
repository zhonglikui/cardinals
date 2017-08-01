package com.zhong.cardinals.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Zhong on 2017/1/28.
 * 关闭资源的工具类
 */

public class CloseUtil {
    private CloseUtil() {
    }

    /**
     * @param closeable 继承自closeable的对象
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
