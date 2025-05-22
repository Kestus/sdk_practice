package ru.kestus.sdk_practice.task1

import androidx.fragment.app.FragmentManager

class Router(
    private val fragmentManager: FragmentManager,
    private val fragmentContainer: Int,
) {

    fun navigateTo(screen: Screens) {
        val fragment = screen.newInstance()
        fragmentManager.beginTransaction()
            .replace(fragmentContainer, fragment)
            .commit()
    }

}