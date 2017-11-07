package com.zhong.cardinals.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhong.cardinals.sample.mode.MarketItem;
import com.zhong.cardinals.util.DialogUtil;
import com.zhong.cardinals.util.SPUtil;
import com.zhong.cardinals.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * cardinals的demo
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(MainActivity.this, R.layout.layout_bottom, null);
                DialogUtil.showBottomDialog(MainActivity.this, view);
            }
        });
        MarketItem marketItem = new MarketItem();
        marketItem.setBuy(113);
        marketItem.setClosePrice(12345);
        marketItem.setSell(211);
        marketItem.setSymbol("jm1801");
        marketItem.setVolume(999);
        List<MarketItem> list = new ArrayList<>();
        list.add(marketItem);
        String key_list = "key_list";
        SPUtil.putList(key_list, list);
        List<MarketItem> newList = SPUtil.getList(key_list, MarketItem.class);
        for (MarketItem i : newList) {
            ToastUtil.showShort(String.valueOf(i.getBuy()));
        }

    }
}
