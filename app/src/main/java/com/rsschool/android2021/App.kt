package com.rsschool.android2021

import android.app.Application
import com.rsschool.android2021.navigation.core.NavStorage

class App : Application() {
    val routersStorage by lazy(LazyThreadSafetyMode.NONE) { NavStorage() }
}