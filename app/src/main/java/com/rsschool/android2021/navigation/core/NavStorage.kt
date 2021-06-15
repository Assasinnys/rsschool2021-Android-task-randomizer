package com.rsschool.android2021.navigation.core

class NavStorage {
    private val navMap = hashMapOf<String, Router>()

    fun addRouter(tag: String, router: Router) = navMap.run {
        if (!containsKey(tag)) put(tag, router)
    }

    fun removeRouter(tag: String) = navMap.remove(tag)

    fun getRouter(tag: String) =
        navMap[tag] ?: throw IllegalArgumentException("Router with this tag not found")
}
