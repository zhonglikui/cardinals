package com.zhong.cardinals.sample.main.demo

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.zhong.cardinals.base.BaseActivity
import com.zhong.cardinals.sample.R
import com.zhong.cardinals.sample.main.demo.recycler.RecycleViewFragment
import com.zhong.cardinals.sample.main.demo.recycler.dummy.DummyContent
import com.zhong.cardinals.util.Logger

class DemoDetailActivity : BaseActivity(), RecycleViewFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_detail)
        var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_detail, RecycleViewFragment.newInstance(3))
        fragmentTransaction.commit()


    }

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        Logger.d("onListFragmentInteraction:" + item.toString())
    }
}
