
package com.demo.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import me.reezy.cosmo.screenadapt.adapt.DesignSizeAdapt

class CustomAdaptActivity : AppCompatActivity(), DesignSizeAdapt {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_adapt)
    }

    fun goCustomAdaptFragment(view: View?) {
        startActivity(Intent(applicationContext, FragmentHost::class.java))
    }

    override val designSize: Int
        get() = 375
}