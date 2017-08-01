package com.zhong.cardinals;


import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.zhong.cardinals.util.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhong on 2017/7/28.
 */

public class SMSObserver extends ContentObserver {
    public static final int RESULT_OF_CODE_WHAT = 101;
    private Handler handler;
    private int codeLength = 4;

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     * @Param codeLength The length of the verification code
     */
    public SMSObserver(Handler handler, int codeLength) {
        super(handler);
        this.handler = handler;
        this.codeLength = codeLength;
    }

    public SMSObserver(Handler handler) {
        super(handler);
        this.handler = handler;
    }

    public Uri getSMSUri() {
        return Uri.parse("content://sms");
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        if (uri != null) {
            Logger.i(uri.toString());
        }
        if (uri.toString().equals("content://sms/raw")) {
            return;
        }
        Uri inboxUri = Uri.parse("content://sms/inbox");
        Cursor cursor = App.getInstance().getContext().getContentResolver().query(inboxUri, null, null, null, "date desc");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String body = cursor.getString(cursor.getColumnIndex("body"));
                Pattern pattern = Pattern.compile("(\\d{" + codeLength + "})");
                Matcher matcher = pattern.matcher(body);
                if (matcher.find()) {
                    String code = matcher.group(0);
                    Message message = Message.obtain();
                    message.obj = code;
                    message.what = RESULT_OF_CODE_WHAT;
                    handler.sendMessage(message);
                }
            }
            cursor.close();
        }
    }
}
