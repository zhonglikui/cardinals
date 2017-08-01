package com.zhong.cardinals.blur;

import android.graphics.Bitmap;
import android.os.Build;
import android.renderscript.RSRuntimeException;

/**
 * Created by zhong on 2017/4/26.
 * 对bitmap进行模糊处理
 */

public class Blur {
    private Blur() {
    }

    /**
     * @param originalBitmap   原始的bitmap
     * @param radius           模糊的半径
     * @param canReuseInBitmap 是否创建新的bitmap进行处理
     * @return 处理完成的bitmap
     */
    public static Bitmap start(Bitmap originalBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            try {
                bitmap = RSBlur.blur(originalBitmap, radius);
            } catch (RSRuntimeException e) {
                bitmap = FastBlur.blur(originalBitmap, radius, canReuseInBitmap);
            }
        } else {
            bitmap = FastBlur.blur(originalBitmap, radius, canReuseInBitmap);
        }
        return bitmap;
    }
}
