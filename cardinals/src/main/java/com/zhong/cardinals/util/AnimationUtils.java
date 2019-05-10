package com.zhong.cardinals.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.zhong.cardinals.App;

import java.util.List;

/**
 * Created by zhong on 2017/3/27.
 * 动画效果工具集
 */

public class AnimationUtils {
    /**
     * 旋转动画
     *
     * @return 旋转动画对象
     */
    public static RotateAnimation getRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(10 * 1000);
        rotateAnimation.setRepeatCount(Integer.MAX_VALUE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setStartOffset(0);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        return rotateAnimation;
    }

    /**
     * 逐帧动画
     *
     * @param images   imagePath的list
     * @param duration 每张显示时长
     * @return 动画对象
     */
    public static AnimationDrawable getAnimationDrawable(List<String> images, int duration) {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (String imagepath : images) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
            Drawable drawable = new BitmapDrawable(App.getInstance().getResources(), bitmap);
            animationDrawable.addFrame(drawable, duration);
        }
        animationDrawable.setOneShot(false);
        return animationDrawable;

    }

}
