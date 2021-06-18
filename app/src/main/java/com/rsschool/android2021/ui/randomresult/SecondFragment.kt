package com.rsschool.android2021.ui.randomresult

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.rsschool.android2021.databinding.FragmentSecondBinding
import com.rsschool.android2021.ui.extentions.MAX_VALUE_KEY
import com.rsschool.android2021.ui.extentions.MIN_VALUE_KEY
import kotlin.random.Random
import kotlin.random.nextInt

class SecondFragment : Fragment() {

    private val random = Random(System.currentTimeMillis())

    private var _binding: FragmentSecondBinding? = null
    private val binding: FragmentSecondBinding get() = requireNotNull(_binding)

    private var backButtonListener: BackButtonCallback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackButtonCallback) backButtonListener = context
    }

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

        result.text = generate(min, max).toString()

        backButton.setOnClickListener {
            backButtonListener?.onBackPressedCall(result.text.toString().toInt())
        }

        /**
         * It's important to specify lifecycle owner to the callback.
         * It disables your callback when the fragment is gone.
         * If you don't specify the lifecycle owner, callback will work even if you go to another
         * fragment. So, it may ruin all navigation.
         */
        requireActivity().onBackPressedDispatcher.addCallback(this@SecondFragment) {
            backButtonListener?.onBackPressedCall(result.text.toString().toInt())
        }
    }

    private fun generate(min: Int, max: Int) = random.nextInt(min..max)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    interface BackButtonCallback {
        fun onBackPressedCall(result: Int)
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val args = Bundle(2).apply {
                putInt(MIN_VALUE_KEY, min)
                putInt(MAX_VALUE_KEY, max)
            }
            return SecondFragment().apply { arguments = args }
        }
    }
}