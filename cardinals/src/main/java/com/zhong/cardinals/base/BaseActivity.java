package com.zhong.cardinals.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.zhong.cardinals.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by zhong on 2017/3/10.
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    //BaseActivity中包含两个泛型参数，第一个是View接口类型，第二个是Presenter的具体类型。
    protected T presenter;//Presenter对象
    protected Dialog progressDialog;
    private List<Call> calls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }*/
        presenter = createPresenter();//创建Presenter
        presenter.attachView((V) this);//View与Presenter建立关联

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        callCancel();

    }

    protected abstract T createPresenter();

    public void addCalls(Call call) {
        if (calls == null) {
            calls = new ArrayList<>();
        }
        calls.add(call);
    }

    private void callCancel() {
        if (calls != null && calls.size() > 0) {
            for (Call call : calls) {
                if (!call.isCanceled())
                    call.cancel();
            }
            calls.clear();
        }
    }

    public void showDialog(String msg) {
        if (!isDestroyed()) {
            if (progressDialog == null) {
                progressDialog = DialogUtil.getProgressDialog(this, null, msg);
            }
            DialogUtil.safeShowDialog(progressDialog);
        }

    }

    public void dismissDialog() {
        DialogUtil.safeCloseDialog(progressDialog);
    }
}
