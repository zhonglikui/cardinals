package com.zhong.cardinals.sample.main.demo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhong.cardinals.base.BaseFragment
import com.zhong.cardinals.sample.R
import com.zhong.cardinals.sample.databinding.FragmentDemoBinding

private const val ARG_TITLE = "param1"

class DemoFragment : BaseFragment(), View.OnClickListener {
    private var _binding: FragmentDemoBinding? = null
    private val binding get() = _binding!!
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bt1.text = title
        binding.bt1.setOnClickListener(this)
    }


    companion object {
        @JvmStatic
        fun newInstance(title: String) =
                DemoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TITLE, title)
                    }
                }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_1 -> {
                var intent: Intent = Intent(activity, DemoDetailActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
