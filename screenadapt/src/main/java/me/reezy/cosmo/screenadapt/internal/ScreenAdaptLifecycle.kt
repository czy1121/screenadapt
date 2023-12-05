package me.reezy.cosmo.screenadapt.internal

import android.app.Activity
import android.app.Application
import android.os.Bundle
import me.reezy.cosmo.screenadapt.ScreenAdaptManager
import me.reezy.cosmo.screenadapt.ScreenAdapter

internal class ScreenAdaptLifecycle : Application.ActivityLifecycleCallbacks {

    var screenAdapter: ScreenAdapter = object : ScreenAdapter {
        override fun adapt(activity: Activity) {
            ScreenAdaptManager.adapt(activity)
        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        screenAdapter.adapt(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        screenAdapter.adapt(activity)
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}