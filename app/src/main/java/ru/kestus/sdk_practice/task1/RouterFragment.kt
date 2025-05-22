package ru.kestus.sdk_practice.task1

import androidx.fragment.app.Fragment

abstract class RouterFragment: Fragment() {

    protected val router by lazy {
        (requireContext() as RouterActivity).router
    }

}