package com.zhong.cardinals;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.zhong.cardinals.imagecropview.ImageCropView;
import com.zhong.cardinals.util.ToastUtil;

import java.io.File;

/**
 * @deprecated
 */
public class PhotoCropActivity extends AppCompatActivity {

    private ImageCropView imageCropView;
    private ProgressBar loaging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_crop);
        imageCropView = (ImageCropView) findViewById(R.id.image);
        loaging = (ProgressBar) findViewById(R.id.loading);
        String uri = getIntent().getStringExtra("path");
        if (uri != null) {
            imageCropView.setImageFilePath(uri, loaging);
        } else {

            ToastUtil.showShort("无法获取图片路径");
        }
    }

    public void onCancle(View v) {
        setResult(RESULT_CANCELED);
        finish();

    }

    public void onSure(View v) {
        if (!imageCropView.isChangingScale()) {
            loaging.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = imageCropView.getCroppedImage();
                    if (null == b) {
                        loaging.setVisibility(View.GONE);
                        ToastUtil.showShort("图片读取失败，请更换图片");
                    } else {
                        File file = PhotoCaptureUtil.saveCroppedImage(b);
                        if (file != null) {
                            Intent intent = new Intent();
                            intent.putExtra("path", file.getPath());
                            setResult(RESULT_OK, intent);
                            finish();
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    loaging.setVisibility(View.GONE);
                                    ToastUtil.showShort("裁剪失败,请重试");
                                }
                            });
                        }
                    }
                }
            }).start();

        }

    }
}
