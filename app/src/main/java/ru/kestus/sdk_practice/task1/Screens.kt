package ru.kestus.sdk_practice.task1

import androidx.fragment.app.Fragment
import ru.kestus.sdk_practice.task1.fragment.FirstFragment
import ru.kestus.sdk_practice.task1.fragment.SecondFragment
import ru.kestus.sdk_practice.task1.fragment.ThirdFragment

enum class Screens(val newInstance: () -> Fragment) {
    FIRST_SCREEN(FirstFragment::newInstance),
    SECOND_SCREEN(SecondFragment::newInstance),
    THIRD_SCREEN(ThirdFragment::newInstance)
}