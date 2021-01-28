package com.zhong.cardinals.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.zhong.cardinals.App;
import com.zhong.cardinals.R;

/**
 * Created by zhong on 17/2/4.
 * Dialog常见工具类
 */

public class DialogUtil {

    /**
     * 默认样式的dialog
     *
     * @param activity
     * @param title
     * @param message
     * @param positiveListener 确认操作
     * @param negativeListener 取消操作
     * @return
     */
    public static Dialog getDialog(Activity activity, String title, @NonNull String message, @NonNull DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {

        return getDialog(activity, title, message, App.getInstance().getString(R.string.str_confirm), positiveListener, App.getInstance().getString(R.string.str_cancel), negativeListener);

    }

    /**
     * @param activity
     * @param title            dialog标题
     * @param message          dialog内容
     * @param positiveStr      确认提示
     * @param positiveListener 确认操作
     * @param negativeStr      取消提示
     * @param negativeListener 取消操作
     * @return
     */
    public static Dialog getDialog(Activity activity, String title, @NonNull String message, String positiveStr, @NonNull DialogInterface.OnClickListener positiveListener, String negativeStr, DialogInterface.OnClickListener negativeListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        if (negativeListener != null) {
            builder.setNegativeButton(negativeStr, negativeListener);
        }

        builder.setPositiveButton(positiveStr, positiveListener);
        builder.setCancelable(false);

        return builder.create();

    }

    /*   public static void showShareDialog(final Activity activity, final Share share) {
           final UMShareListener umShareListener = new UMShareListener() {
               @Override
               public void onStart(SHARE_MEDIA share_media) {

               }

               @Override
               public void onResult(SHARE_MEDIA platform) {
                   // Toast.makeText(MainActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
                   ToastUtil.showShort(App.getInstance().getResources().getString(R.string.share_success));
               }

               @Override
               public void onError(SHARE_MEDIA platform, Throwable t) {
                   //Toast.makeText(MainActivity.this, "分享失败", Toast.LENGTH_SHORT).show();
               }

               @Override
               public void onCancel(SHARE_MEDIA platform) {
                   //Toast.makeText(MainActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
               }
           };

           final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
           View view = View.inflate(activity, R.layout.layout_share_window, null);
           view.findViewById(R.id.tv_share_to_weixin).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
                   UmengManager.getInstance().toShare(activity, share, SHARE_MEDIA.WEIXIN, umShareListener);

               }
           });
           view.findViewById(R.id.tv_share_to_weixin_circle).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
                   UmengManager.getInstance().toShare(activity, share, SHARE_MEDIA.WEIXIN_CIRCLE, umShareListener);

               }
           });
           view.findViewById(R.id.tv_share_to_weibo).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
                   UmengManager.getInstance().toShare(activity, share, SHARE_MEDIA.SINA, umShareListener);

               }
           });
           view.findViewById(R.id.tv_share_to_qq).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
                   UmengManager.getInstance().toShare(activity, share, SHARE_MEDIA.QQ, umShareListener);

               }
           });
           view.findViewById(R.id.tv_share_to_qq_zone).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
                   UmengManager.getInstance().toShare(activity, share, SHARE_MEDIA.QZONE, umShareListener);
               }
           });
           //短信
           view.findViewById(R.id.tv_share_to_messaage).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
                   share.setImage(null);
                   share.setFile(null);
                   Uri uri = Uri.parse("smsto:" + "");
                   Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
                   sendIntent.putExtra("sms_body", App.getInstance().getString(R.string.sms_content));
                   activity.startActivity(sendIntent);
                   // UmengManager.getInstance().toShare(activity, share, SHARE_MEDIA.SMS, umShareListener);
               }
           });
           view.findViewById(R.id.close_btn).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   dialog.dismiss();
               }
           });

           WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
           lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
           lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
           lp.gravity = Gravity.BOTTOM;
           dialog.getWindow().setAttributes(lp);
           dialog.setContentView(view);
           dialog.setCancelable(true);
           dialog.setCanceledOnTouchOutside(true);
           dialog.show();
       }
   */
    //等待Dialog
    public static ProgressDialog getProgressDialog(Activity activity, String title, String message) {
        ProgressDialog dialog = new ProgressDialog(activity);
        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            dialog.setMessage(message);
        }
        return dialog;
    }


    //底部dialog
    public static void showBottomDialog(final Activity activity, View view) {


        final AlertDialog dialog = new AlertDialog.Builder(activity).setCancelable(true).create();
        dialog.show();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        if (lp != null) {

            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            dialog.getWindow().setAttributes(lp);
        }
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(view);


    }

    public static AlertDialog getCustomDialog(final Activity activity, View view) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(activity).setCancelable(true);
        dialog.setView(view);
        return dialog.create();
    }


    public static Dialog showSingleItemDialog(Activity activity, String title, int itemsId, int checkedItem, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setSingleChoiceItems(itemsId, checkedItem, onClickListener);
        return builder.show();
    }

    public static Dialog showItemDialog(Activity activity, String title, int itemsId, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setItems(itemsId, onClickListener);
        return builder.show();
    }

    public static Dialog showItemDialog(Activity activity, String title, String[] itemsId, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        builder.setItems(itemsId, onClickListener);
        return builder.show();
    }

    /**
     * 关闭Dialog
     */
    public static void safeCloseDialog(Dialog dialog) {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示Dialog
     */
    public static void safeShowDialog(Dialog dialog) {
        try {
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
