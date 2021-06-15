package com.rsschool.android2021.ui.destinations

import com.rsschool.android2021.navigation.core.Destination
import com.rsschool.android2021.ui.main.FirstFragment
import com.rsschool.android2021.ui.randomresult.SecondFragment

enum class Screens {
    MAIN_FRAGMENT {
        override fun getDestination() = Destination(
            fragmentFactoryMethod = FirstFragment.Companion::newInstance,
            isBackStackEnabled = false,
            tag = FirstFragment::class.simpleName
        )
    },
    RESULT_FRAGMENT {
        override fun getDestination() = Destination(
            fragmentFactoryMethod = SecondFragment.Companion::newInstance,
            isBackStackEnabled = true,
            tag = SecondFragment::class.simpleName
        )
    };

    abstract fun getDestination(): Destination
}