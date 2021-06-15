package com.rsschool.android2021.ui.base

import androidx.fragment.app.Fragment
import com.rsschool.android2021.navigation.core.Router
import com.rsschool.android2021.navigation.core.Router.Companion.ROUTER_BUNDLE_KEY
import com.rsschool.android2021.ui.extentions.getApp

abstract class BaseFragment : Fragment() {

    protected val router: Router by lazy {
        val routerTag = arguments?.getSerializable(ROUTER_BUNDLE_KEY) as String
        getApp().routersStorage.getRouter(routerTag)
    }
}
