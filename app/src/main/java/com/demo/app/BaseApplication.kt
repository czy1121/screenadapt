package com.demo.app

import android.app.Activity
import android.util.Log
import me.reezy.cosmo.screenadapt.ScreenAdapt
import me.reezy.cosmo.screenadapt.ScreenAdaptManager
import me.reezy.cosmo.screenadapt.ScreenAdapter
import me.reezy.cosmo.screenadapt.Subunit

class BaseApplication : android.app.Application() {
    override fun onCreate() {
        super.onCreate()
        ScreenAdapt.init(this).setDesignSize(375).setLogEnable(true)
    }
}