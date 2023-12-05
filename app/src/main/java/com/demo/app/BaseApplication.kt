package com.demo.app

import android.app.Activity
import android.util.Log
import me.reezy.cosmo.screenadapt.ScreenAdapt
import me.reezy.cosmo.screenadapt.ScreenAdaptManager
import me.reezy.cosmo.screenadapt.ScreenAdapter

class BaseApplication : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        ScreenAdapt.init(this).setLogEnable(true).setScreenAdapter(object : ScreenAdapter {
            override fun adapt(activity: Activity) {
                Log.e("OoO", "${activity.javaClass.name} adapt before!")
                ScreenAdaptManager.adapt(activity)
                Log.e("OoO", "${activity.javaClass.name} adapt after!")
            }
        })
    }
}