package com.demo.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.reezy.cosmo.screenadapt.adapt.DesignSizeAdapt


class FragmentHost : AppCompatActivity(), DesignSizeAdapt {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        if (supportFragmentManager.findFragmentById(R.id.container1) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container1, CustomFragment1()).commit()
        }
        if (supportFragmentManager.findFragmentById(R.id.container2) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container2, CustomFragment2()).commit()
        }
        if (supportFragmentManager.findFragmentById(R.id.container3) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container3, CustomFragment3()).commit()
        }
    }

    override val designSize: Int
        get() = 720
}