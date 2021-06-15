package com.rsschool.android2021.ui.randomresult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rsschool.android2021.databinding.FragmentSecondBinding
import com.rsschool.android2021.ui.base.BaseFragment
import com.rsschool.android2021.ui.extentions.MAX_VALUE_KEY
import com.rsschool.android2021.ui.extentions.MIN_VALUE_KEY
import com.rsschool.android2021.ui.extentions.getMainActivity
import kotlin.random.Random
import kotlin.random.nextInt

class SecondFragment : BaseFragment() {

    private val random = Random(System.currentTimeMillis())

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() = binding.run {
        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result.text = generate(min, max).let {
            getMainActivity().previousResult = it
            it.toString()
        }

        backButton.setOnClickListener { router.callBackStack() }
    }

    private fun generate(min: Int, max: Int) = random.nextInt(min..max)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {

        @JvmStatic
        fun newInstance(args: Bundle?): SecondFragment {
            return SecondFragment().apply { arguments = args }
        }

        @Deprecated("old java version")
        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            return fragment
        }

    }
}