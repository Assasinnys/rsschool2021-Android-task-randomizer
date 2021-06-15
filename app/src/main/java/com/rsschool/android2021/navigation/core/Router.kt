package com.rsschool.android2021.navigation.core

import java.io.Serializable

interface Router {
    fun navigate(destination: Destination, args: Map<String, Serializable?>? = null)
    fun callBackStack(destination: Destination? = null)

    companion object {
        const val ROUTER_BUNDLE_KEY  = "router_key"
    }
}

