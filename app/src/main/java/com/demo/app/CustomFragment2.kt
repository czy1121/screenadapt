
package com.demo.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.reezy.cosmo.screenadapt.ScreenAdaptManager

class CustomFragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ScreenAdaptManager.adapt(resources, 720)
        return CustomFragment1.createTextView(inflater, "Fragment-2\nView width = 360dp\nTotal width = 720dp", -0xff0100)
    }
}