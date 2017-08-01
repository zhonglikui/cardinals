package com.zhong.cardinals.util;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.zhong.cardinals.App;

/**
 * Created by zhong on 2017/2/9.
 * 手机常见工具方法类
 */

public class DevicesUtil {
    /**
     * 获取手机屏幕的高度
     *
     * @return 手机屏幕的高度
     */
    public static int getHeightPixels() {
// Resources.getSystem().getDisplayMetrics().heightPixels;
        WindowManager wm = (WindowManager) App.getInstance().getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取手机屏幕的宽度
     *
     * @return 手机屏幕的宽度
     */
    public static int getWidthPixels() {
        WindowManager wm = (WindowManager) App.getInstance().getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 判断是是否有录音权限
     */
    public static boolean isCanUseAudio(Activity activity) {
  /*      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//6.0以上系统
            if (ContextCompat.checkSelfPermission(activity,Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED) {
                return false;
            }else{
                return true;
            }

        }else {//6.0以下系统*/
        // 音频获取源
        int audioSource = MediaRecorder.AudioSource.MIC;
        // 设置音频采样率，44100是目前的标准，但是某些设备仍然支持22050，16000，11025
        int sampleRateInHz = 44100;
        // 设置音频的录制的声道CHANNEL_IN_STEREO为双声道，CHANNEL_CONFIGURATION_MONO为单声道
        int channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        // 音频数据格式:PCM 16位每个样本。保证设备支持。PCM 8位每个样本。不一定能得到设备支持。
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        // 缓冲区字节大小
        int bufferSizeInBytes;
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        AudioRecord audioRecord = new AudioRecord(audioSource, sampleRateInHz,
                channelConfig, audioFormat, bufferSizeInBytes);
        //开始录制音频
        try {
            // 防止某些手机崩溃，例如联想
            audioRecord.startRecording();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        //根据开始录音判断是否有录音权限
        if (audioRecord.getRecordingState() != AudioRecord.RECORDSTATE_RECORDING) {
            // context.startActivity(new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS));
            return false;
        } else {
            audioRecord.stop();
            audioRecord.release();
            return true;
        }

        // }


    }

    /**
     * 测试当前摄像头能否被使用
     *
     * @return true可用，false不可用
     */
    public static boolean isCameraCanUse(Activity activity) {
        boolean canUseCamera;
     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//6.0以上系统
            if (ContextCompat.checkSelfPermission(activity,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED) {
                return false;
            }else{
                return true;
            }

        }else {*/
        Camera mCamera = null;
        try {
            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            canUseCamera = true;
        } catch (Exception e) {
            canUseCamera = false;
        }
        if (canUseCamera) {
            mCamera.release();
        }

        // }

        return canUseCamera;
    }



    /**
     * 获取手机的唯一识别码
     *
     * @param context Context对象
     * @return 手机的IMEI识别码
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

/*    public static String getPhoneInfo(Context context) {
        TelephonyManager tm = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();
        sb.append("\n设备Id(IMEI) = " + tm.getDeviceId());// 15位数字的设备id
        sb.append("\n手机型号：" + android.os.Build.MODEL);//例如GT-I9502
        sb.append("\n系统名称：" + android.os.Build.DISPLAY);
        sb.append("\n设备名称：" + android.os.Build.DEVICE);// m1metal--HM2014011--ja3gchnduos
        sb.append("\n硬件：" + android.os.Build.HARDWARE);// 一般显示处理器型号

        sb.append("\nID：" + android.os.Build.ID);// LMY47I--HM2014011--LRX22C
        sb.append("\n手机7：" + android.os.Build.TAGS);// 手机一般显示release-keys，可以区分是不是模拟器

        sb.append("\n用户类型：" + android.os.Build.TYPE);// user--user--user

        sb.append("\n手机11：" + android.os.Build.VERSION.CODENAME);// REL
        sb.append("\nandroid版本：" + android.os.Build.VERSION.RELEASE);//android版本例如 4.4.2
        sb.append("\nandroidApi版本：" + android.os.Build.VERSION.SDK_INT);//手机的android APi 19
        sb.append("\n手机14：" + android.os.Build.VERSION_CODES.BASE);// 1--1--1
        Logger.i("info:" + sb.toString());
        return sb.toString();
    }*/


}
