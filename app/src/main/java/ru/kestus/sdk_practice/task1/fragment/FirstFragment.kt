package ru.kestus.sdk_practice.task1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ru.kestus.sdk_practice.R
import ru.kestus.sdk_practice.task1.RouterFragment
import ru.kestus.sdk_practice.task1.Screens


class FirstFragment : RouterFragment() {

    private lateinit var buttonNext: Button
    private lateinit var buttonPrevious: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        buttonNext = view.findViewById(R.id.btn_next_fragment)
        buttonPrevious = view.findViewById(R.id.btn_previous_fragment)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonNext.setOnClickListener {
            router.navigateTo(Screens.SECOND_SCREEN)
        }
        buttonPrevious.setOnClickListener {
            router.navigateTo(Screens.THIRD_SCREEN)
        }
    }

    companion object {
        fun newInstance() = FirstFragment()
    }
}