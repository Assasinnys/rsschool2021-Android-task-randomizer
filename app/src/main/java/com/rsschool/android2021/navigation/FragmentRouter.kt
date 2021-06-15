package com.rsschool.android2021.navigation

import android.os.Bundle
import androidx.annotation.IntegerRes
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.rsschool.android2021.navigation.core.Destination
import com.rsschool.android2021.navigation.core.Router
import com.rsschool.android2021.navigation.core.Router.Companion.ROUTER_BUNDLE_KEY
import java.io.Serializable
import java.lang.ref.WeakReference

class FragmentRouter(
    fragmentManager: FragmentManager,
    @IntegerRes private val container: Int,
    private val routerTag: String
) : Router {

    private val safeFragmentManager = WeakReference(fragmentManager)

    override fun navigate(destination: Destination, args: Map<String, Serializable?>?) {
        val bundle = generateFragmentBundle(args)

        safeFragmentManager.get()?.beginTransaction()?.run {
            replace(container, destination.fragmentFactoryMethod(bundle))
            if (destination.isBackStackEnabled) addToBackStack(destination.tag)
            commit()
        }
    }

    override fun callBackStack(destination: Destination?) {
        safeFragmentManager.get()?.popBackStack(destination?.tag, 0)
    }

    private fun generateFragmentBundle(args: Map<String, Serializable?>?): Bundle {
        return args?.toRouterBundle() ?: bundleOf(ROUTER_BUNDLE_KEY to routerTag)
    }

    private fun Map<String, Serializable?>.toRouterBundle() = Bundle(size.inc()).apply {
        val keys = this@toRouterBundle.keys
        for (key in keys) {
            putSerializable(key, this@toRouterBundle[key])
        }
        putSerializable(ROUTER_BUNDLE_KEY, routerTag)
    }
}
