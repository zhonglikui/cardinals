package com.zhong.cardinals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * @deprecated
 */
public class PhotoCropActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent();
        intent.putExtra("path", "");
        setResult(RESULT_OK, intent);
        finish();
    }


}
