package com.rsschool.android2021.navigation.core

import android.os.Bundle
import com.rsschool.android2021.ui.base.BaseFragment

open class Destination(
    val fragmentFactoryMethod: (Bundle) -> BaseFragment,
    val isBackStackEnabled: Boolean,
    val tag: String?
)