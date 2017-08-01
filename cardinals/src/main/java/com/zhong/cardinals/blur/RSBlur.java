package com.zhong.cardinals.blur;

import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import com.zhong.cardinals.App;

/**
 * 模糊半径(radius)越大，性能要求越高，模糊半径不能超过25，所以并不能得到模糊度非常高的图片。
 * ScriptIntrinsicBlur在API 17时才被引入
 */

class RSBlur {

    static Bitmap blur(Bitmap bitmap, int radius) throws RSRuntimeException {
        RenderScript rs = null;
        radius = radius > 25 ? 25 : radius;//模糊半径不能超过25
        try {
            rs = RenderScript.create(App.getInstance().getContext());
            rs.setMessageHandler(new RenderScript.RSMessageHandler());
            Allocation input = Allocation.createFromBitmap(rs, bitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            Allocation output = Allocation.createTyped(rs, input.getType());
            ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            blur.setInput(input);
            blur.setRadius(radius);
            blur.forEach(output);
            output.copyTo(bitmap);
        } finally {
            if (rs != null) {
                rs.destroy();
            }
        }


        return bitmap;
    }
}
