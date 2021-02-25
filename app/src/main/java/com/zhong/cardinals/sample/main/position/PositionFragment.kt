package com.zhong.cardinals.sample.main.position

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zhong.cardinals.sample.R

class PositionFragment : Fragment() {

    companion object {
        fun newInstance() = PositionFragment()
    }

    private lateinit var viewModel: PositionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.position_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PositionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}