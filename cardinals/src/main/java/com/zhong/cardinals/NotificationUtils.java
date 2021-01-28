package com.zhong.cardinals;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationManagerCompat;

public class NotificationUtils {
    /**
     * @return 通知栏是否开启
     */
    public static boolean isNotificationEnable() {
        return NotificationManagerCompat.from(App.getInstance().getContext()).areNotificationsEnabled();

    }

    public static void openNotification() {
        Intent intent = new Intent();
        String packageName = App.getInstance().getContext().getPackageName();
        if (Build.VERSION.SDK_INT >= 26) {
            // android 8.0引导
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
        } else if (Build.VERSION.SDK_INT >= 21) {
            // android 5.0-7.0
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra("app_package", packageName);
            intent.putExtra("app_uid", App.getInstance().getContext().getApplicationInfo().uid);
        } else {
            // 其他
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts("package", packageName, null));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getInstance().getContext().startActivity(intent);
    }
}
