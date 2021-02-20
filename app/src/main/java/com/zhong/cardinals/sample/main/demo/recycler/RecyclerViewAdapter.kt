package com.zhong.cardinals.sample.main.demo.recycler

import android.app.Activity
import android.widget.TextView
import com.zhong.cardinals.adapter.BaseRecycleAdapter
import com.zhong.cardinals.adapter.RecycleViewHolder
import com.zhong.cardinals.sample.R
import com.zhong.cardinals.sample.main.demo.recycler.dummy.DummyContent.DummyItem

class RecyclerViewAdapter(activity: Activity?, layoutId: Int) : BaseRecycleAdapter<DummyItem?>(activity, layoutId) {
    override fun convert(position: Int, holder: RecycleViewHolder?, item: DummyItem?) {
        var mIdView: TextView? = holder?.getView(R.id.item_number)
        var mContentView: TextView? = holder?.getView(R.id.content)
        var mRemove1View: TextView? = holder?.getView(R.id.item_remove1)
        var mRemove2View: TextView? = holder?.getView(R.id.item_remove2)
        mIdView?.text = item?.id
        mContentView?.text = item?.content
        mRemove1View?.text = "删除1"
        mRemove2View?.text = "删除2"
        mRemove1View?.setOnClickListener {
            removeItem(position)
        }
        mRemove2View?.setOnClickListener {
            removeItem(item)
        }
    }


}