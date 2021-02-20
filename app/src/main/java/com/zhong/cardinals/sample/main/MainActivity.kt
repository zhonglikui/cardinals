package com.zhong.cardinals.sample.main

import android.os.Bundle
import com.zhong.cardinals.adapter.BaseFragmentAdapter
import com.zhong.cardinals.base.BaseActivity
import com.zhong.cardinals.sample.R
import com.zhong.cardinals.sample.databinding.ActivityMainBinding
import com.zhong.cardinals.sample.main.account.AccountFragment
import com.zhong.cardinals.sample.main.demo.DemoFragment
import com.zhong.cardinals.sample.main.market.MarketFragment

/**
 * cardinals的demo
 */
class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    var fragmentAdapter: BaseFragmentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentAdapter = BaseFragmentAdapter(this)
        binding.vpHome.adapter = fragmentAdapter
        fragmentAdapter!!.add(DemoFragment.newInstance("demo"))
        fragmentAdapter!!.add(MarketFragment.newInstance())
        fragmentAdapter!!.add(AccountFragment.newInstance())
        fragmentAdapter!!.notifyDataSetChanged()
        binding.rg.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_demo -> {
                    binding.vpHome.currentItem = 0
                }
                R.id.rb_market -> {
                    binding.vpHome.currentItem = 1
                }
                R.id.rb_account -> {
                    binding.vpHome.currentItem = 2
                }
            }
        }
        binding.vpHome.isUserInputEnabled = false
        binding.vpHome.offscreenPageLimit

    }
}
