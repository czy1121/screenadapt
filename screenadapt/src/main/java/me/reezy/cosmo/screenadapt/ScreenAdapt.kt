package me.reezy.cosmo.screenadapt

import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import me.reezy.cosmo.screenadapt.internal.DensityManager
import me.reezy.cosmo.screenadapt.internal.ScreenAdaptLifecycle

object ScreenAdapt {

    private var application: Application? = null

    private var lifecycle: ScreenAdaptLifecycle = ScreenAdaptLifecycle()

    private var isPaused: Boolean = false
    private var isLogEnabled: Boolean = false

    var designSize: Int = 360
        private set

    fun init(app: Application): ScreenAdapt {
        if (application != null) return this

        application = app

        DensityManager.init(app)

        app.registerComponentCallbacks(object : ComponentCallbacks {
            override fun onConfigurationChanged(newConfig: Configuration) {
                if (newConfig.fontScale > 0) {
                    DensityManager.setSystemScaledDensity(Resources.getSystem().displayMetrics.scaledDensity)
                }
            }

            override fun onLowMemory() {
            }
        })

        app.registerActivityLifecycleCallbacks(lifecycle)

        return this
    }


    fun resume() {
        synchronized(ScreenAdapt) {
            if (isPaused) {
                application?.registerActivityLifecycleCallbacks(lifecycle)
                isPaused = false
            }
        }
    }

    fun pause() {
        synchronized(ScreenAdapt) {
            if (!isPaused) {
                application?.unregisterActivityLifecycleCallbacks(lifecycle)
                isPaused = true
            }
        }
    }

    fun setDesignSize(designSize: Int): ScreenAdapt {
        check(designSize > 0) { "designSize must be > 0" }
        this.designSize = designSize
        return this
    }

    fun setSupportSP(value: Boolean): ScreenAdapt {
        DensityManager.isSupportSP = value
        return this
    }

    fun setSupportDP(value: Boolean): ScreenAdapt {
        DensityManager.isSupportDP = value
        return this
    }

    fun setSubunit(subunit: Subunit): ScreenAdapt {
        DensityManager.subunit = subunit
        return this
    }


    fun setFontScale(fontScale: Float): ScreenAdapt {
        DensityManager.fontScale = fontScale
        return this
    }

    fun setExcludeSystemFontScale(value: Boolean): ScreenAdapt {
        DensityManager.isExcludeSystemFontScale = value
        return this
    }

    fun setScreenAdapter(adapter: ScreenAdapter): ScreenAdapt {
        lifecycle.screenAdapter = adapter
        return this
    }

    fun setLogEnable(enable: Boolean): ScreenAdapt {
        isLogEnabled = enable
        return this
    }

    internal fun log(message: String) {
        if (isLogEnabled) {
            Log.w("OoO.ScreenAdapt", message)
        }
    }
}