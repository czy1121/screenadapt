package me.reezy.cosmo.screenadapt.internal

import android.app.Activity
import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import me.reezy.cosmo.screenadapt.ScreenAdaptManager
import me.reezy.cosmo.screenadapt.ScreenAdapter
import java.lang.ref.WeakReference

internal class ScreenAdaptLifecycle : Application.ActivityLifecycleCallbacks {

    var screenAdapter: ScreenAdapter = object : ScreenAdapter {
        override fun adapt(activity: Activity) {
            ScreenAdaptManager.adapt(activity)
        }
    }

    private var resumed: WeakReference<Activity>? = null

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        screenAdapter.adapt(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        screenAdapter.adapt(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        if (Build.VERSION.SDK_INT > 34) {
            resumed = WeakReference(activity)
        }
    }

    override fun onActivityPaused(activity: Activity) {
        if (Build.VERSION.SDK_INT > 34) {
            resumed = null
        }
    }

    override fun onActivityPreStopped(activity: Activity) {
        if (Build.VERSION.SDK_INT > 34) {
            resumed?.get()?.let {
                screenAdapter.adapt(it)
            }
        }
    }

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}


    override fun onActivityDestroyed(activity: Activity) {}
}