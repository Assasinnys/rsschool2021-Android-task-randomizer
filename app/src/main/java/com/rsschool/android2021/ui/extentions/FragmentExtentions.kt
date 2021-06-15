package com.rsschool.android2021.ui.extentions

import androidx.fragment.app.Fragment
import com.rsschool.android2021.App
import com.rsschool.android2021.ui.main.MainActivity

fun Fragment.getApp() = requireActivity().application as App
fun Fragment.getMainActivity() = requireActivity() as MainActivity