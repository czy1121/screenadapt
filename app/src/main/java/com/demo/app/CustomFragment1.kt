package com.demo.app

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import me.reezy.cosmo.screenadapt.ScreenAdaptManager
import me.reezy.cosmo.screenadapt.adapt.DesignSizeAdapt

class CustomFragment1 : Fragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ScreenAdaptManager.adapt(resources, 1080)
        return createTextView(inflater, "Fragment-1\nView width = 360dp\nTotal width = 1080dp", -0x10000)
    }

    companion object {
        @JvmStatic
        fun createTextView(inflater: LayoutInflater, content: String?, backgroundColor: Int): View {
            val view = TextView(inflater.context)
            val layoutParams = ViewGroup.LayoutParams(
                (inflater.context.resources.displayMetrics.density * 360).toInt(),
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            view.layoutParams = layoutParams
            view.text = content
            view.setTextColor(-0x1)
            view.gravity = Gravity.CENTER
            view.textSize = 30f
            view.setBackgroundColor(backgroundColor)
            return view
        }
    }
}