package com.zhong.cardinals;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.zhong.cardinals.util.ToastUtil;

/**
 * 监听网络连接状态
 */
public class ConnectionChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 判断网络是否已连接
            NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
            if (null == activeNetInfo) {
                // UIUtils.showToast(context, "网络已断开");
                ToastUtil.showShort(App.getInstance().getString(R.string.network_disconnected));
                return;
            }

            /*switch (activeNetInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                case ConnectivityManager.TYPE_MOBILE_MMS:
                case ConnectivityManager.TYPE_MOBILE_SUPL:
                case ConnectivityManager.TYPE_MOBILE_DUN:
                case ConnectivityManager.TYPE_MOBILE_HIPRI:
                    try {
                        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        switch (tm.getNetworkType()) {
                            case TelephonyManager.NETWORK_TYPE_EDGE:
                            case TelephonyManager.NETWORK_TYPE_GPRS:
                                // UIUtils.showToast(context, "您正在使用的网络速度较慢，建议在wifi下进行使用");
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case ConnectivityManager.TYPE_WIFI:

                default:
                    break;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
