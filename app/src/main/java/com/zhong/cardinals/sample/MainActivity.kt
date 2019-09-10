package com.zhong.cardinals.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

import com.zhong.cardinals.util.DialogUtil

/**
 * cardinals的demo
 */
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.bt_main).setOnClickListener {
            val view = View.inflate(this@MainActivity, R.layout.layout_bottom, null)
            DialogUtil.showBottomDialog(this@MainActivity, view)
        }


    }
}
