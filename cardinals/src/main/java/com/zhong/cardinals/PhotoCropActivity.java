package com.zhong.cardinals;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zhong.cardinals.util.Logger;

/**
 * @deprecated
 */
public class PhotoCropActivity extends AppCompatActivity {
    public static String PARAM_PATH = "path";
    String resultPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resultPath = getIntent().getStringExtra(PARAM_PATH);
        Logger.d("裁剪后返回的path:" + resultPath);
        Intent intent = new Intent();
        intent.putExtra(PARAM_PATH, resultPath);
        setResult(RESULT_OK, intent);
        finish();
    }


}
