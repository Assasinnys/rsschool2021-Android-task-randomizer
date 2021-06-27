package com.rsschool.android2021.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rsschool.android2021.R
import com.rsschool.android2021.databinding.FragmentFirstBinding
import com.rsschool.android2021.ui.base.BaseFragment
import com.rsschool.android2021.ui.destinations.Screens
import com.rsschool.android2021.ui.extentions.*

class FirstFragment : BaseFragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() = binding.run {
        previousResult.text =
            getString(R.string.previous_result_text, getMainActivity().previousResult)

        generateButton.setOnClickListener {
            val min: Long? = minValue.editText?.text?.toString()?.toLongOrNull()
            val max: Long? = maxValue.editText?.text?.toString()?.toLongOrNull()
            if (areFieldsValid(min, max)) {
                val args = mapOf(MIN_VALUE_KEY to min?.toInt(), MAX_VALUE_KEY to max?.toInt())
                router.navigate(Screens.RESULT_FRAGMENT.getDestination(), args)
            }
        }
    }

    /**
     * Field validation is a pain of the Kotlin.. =(
     */
    private fun areFieldsValid(min: Long?, max: Long?): Boolean {
        var isValid = true

        if (min == null) {
            binding.minValue.callError(R.string.error_min_null)
            isValid = false
        } else {
            binding.minValue.clearError()
        }

        if (max == null) {
            binding.maxValue.callError(R.string.error_max_null)
            isValid = false
        } else {
            binding.maxValue.clearError()
        }

        if (min != null && max != null) {
            isValid = isContentValid(min, max)
        }

        return isValid
    }

    private fun isContentValid(min: Long, max: Long): Boolean {
        return when {
            min > max -> {
                binding.minValue.callError(R.string.error_min_more_max)
                binding.maxValue.callError(R.string.error_min_more_max)
                false
            }
            min > Int.MAX_VALUE -> {
                binding.minValue.callError(R.string.error_min_more_int)
                false
            }
            max > Int.MAX_VALUE -> {
                binding.maxValue.callError(R.string.error_max_more_int)
                false
            }
            else -> true
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(args: Bundle?): FirstFragment {
            return FirstFragment().apply { arguments = args }
        }

        @Deprecated("old java version")
        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }
    }
}