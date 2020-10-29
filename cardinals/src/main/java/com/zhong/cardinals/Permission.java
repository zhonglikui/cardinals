package com.zhong.cardinals;

import android.content.pm.PackageManager;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.zhong.cardinals.util.Logger;

/**
 * Created by zhong on 2017/6/15.
 * 动态权限相关
 */

public class Permission {
    //检查权限

    public static void checkSelfPermission(AppCompatActivity activity, String permission, int request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以上才支持
            //获取已经被
            //  List<String> deniedPermissions = findDeniedPermissions(activity, permission);
          /*  Logger.i("deniedPermissions size:"+deniedPermissions.size());
            if (deniedPermissions!=null&&deniedPermissions.size()>0){
                activity.requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), request);
            }*/
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                //没有被授权

                //是否需要解释
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    //向用户解释，看到解释，再次尝试请求许可。
                    Logger.i(permission + " : 需要解释");
                } else {
                    //不需要解释，可以向用户请求许可
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, request);
                    Logger.i(permission + " : 不需要解释");
                }
                //申请权限

            } else {
                //
                Logger.i(permission + " : 已经授权");
            }
        }

    }

/*    private static List<String> findDeniedPermissions(Activity activity, String[] permissions) {
        List<String> denyPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(permission);
            }
        }

        return denyPermissions;
    }*/


    /**
     * Requests permission.
     *
     * @param activity
     * @param requestCode request code, e.g. if you need request CAMERA permission,parameters is PermissionUtils.CODE_CAMERA
     */
 /*   public static void requestPermission(final Activity activity, final int requestCode, PermissionGrant permissionGrant) {
        if (activity == null) {
            return;
        }

        Log.i(TAG, "requestPermission requestCode:" + requestCode);
        if (requestCode < 0 || requestCode >= requestPermissions.length) {
            Log.w(TAG, "requestPermission illegal requestCode:" + requestCode);
            return;
        }

        final String requestPermission = requestPermissions[requestCode];

        //如果是6.0以下的手机，ActivityCompat.checkSelfPermission()会始终等于PERMISSION_GRANTED，
        // 但是，如果用户关闭了你申请的权限(如下图，在安装的时候，将一些权限关闭了)，ActivityCompat.checkSelfPermission()则可能会导致程序崩溃(java.lang.RuntimeException: Unknown exception code: 1 msg null)，
        // 你可以使用try{}catch(){},处理异常，也可以判断系统版本，低于23就不申请权限，直接做你想做的。permissionGrant.onPermissionGranted(requestCode);
//        if (Build.VERSION.SDK_INT < 23) {
//            permissionGrant.onPermissionGranted(requestCode);
//            return;
//        }

        int checkSelfPermission;
        try {
            checkSelfPermission = ActivityCompat.checkSelfPermission(activity, requestPermission);
        } catch (RuntimeException e) {
            Toast.makeText(activity, "please open this permission", Toast.LENGTH_SHORT)
                    .show();
            Log.e(TAG, "RuntimeException:" + e.getMessage());
            return;
        }

        if (checkSelfPermission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "ActivityCompat.checkSelfPermission != PackageManager.PERMISSION_GRANTED");


            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, requestPermission)) {
                Log.i(TAG, "requestPermission shouldShowRequestPermissionRationale");
                shouldShowRationale(activity, requestCode, requestPermission);

            } else {
                Log.d(TAG, "requestCameraPermission else");
                ActivityCompat.requestPermissions(activity, new String[]{requestPermission}, requestCode);
            }

        } else {
            Log.d(TAG, "ActivityCompat.checkSelfPermission ==== PackageManager.PERMISSION_GRANTED");
            Toast.makeText(activity, "opened:" + requestPermissions[requestCode], Toast.LENGTH_SHORT).show();
//得到权限的时候，就可以在回调里面做你想做的事情了
            permissionGrant.onPermissionGranted(requestCode);
        }
    }*/
}

