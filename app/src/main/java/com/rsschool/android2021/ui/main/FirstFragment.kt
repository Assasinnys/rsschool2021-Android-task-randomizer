package com.rsschool.android2021.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.rsschool.android2021.R
import com.rsschool.android2021.databinding.FragmentFirstBinding
import com.rsschool.android2021.ui.extentions.*

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding: FragmentFirstBinding get() = requireNotNull(_binding)

    private var generateButtonListener: GenerationCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GenerationCallback) generateButtonListener = context
    }

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
            getString(R.string.previous_result_text, generateButtonListener?.getPreviousResult())

        generateButton.setOnClickListener {
            val min: Long? = minValue.editText?.text?.toString()?.toLongOrNull()
            val max: Long? = maxValue.editText?.text?.toString()?.toLongOrNull()
            if (areFieldsValid(min, max)) {
                generateButtonListener?.onGenerateButtonClicked(min?.toInt() ?: 0, max?.toInt() ?: 0)
            }
        }
    }

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
                binding.minValue.callError(R.string.error_min_more_max)
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

    interface GenerationCallback {
        fun onGenerateButtonClicked(min: Int, max: Int)
        fun getPreviousResult(): Int
    }

    companion object {
        @JvmStatic
        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }
}