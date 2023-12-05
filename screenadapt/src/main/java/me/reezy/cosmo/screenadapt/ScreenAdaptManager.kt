package me.reezy.cosmo.screenadapt

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import me.reezy.cosmo.screenadapt.adapt.CancelAdapt
import me.reezy.cosmo.screenadapt.adapt.DesignSizeAdapt
import me.reezy.cosmo.screenadapt.internal.DensityManager


object ScreenAdaptManager {

    private val mCancelList = mutableListOf<String>()
    private val mDesignSizeMap = mutableMapOf<String, Int>()

    val hasCancelAdapt: Boolean get() = mCancelList.isNotEmpty()
    val hasDesignSizeAdapt: Boolean get() = mDesignSizeMap.isNotEmpty()


    fun addCancelAdapt(clazz: Class<out Any>): ScreenAdaptManager {
        clazz.canonicalName?.let { mCancelList.add(it) }
        return this
    }

    fun addDesignSizeAdapt(clazz: Class<out Any>, designSize: Int): ScreenAdaptManager {
        clazz.canonicalName?.let { mDesignSizeMap.put(it, designSize) }
        return this
    }

    fun isCancelAdapt(clazz: Class<Any>): Boolean {
        val name = clazz.canonicalName ?: return false
        return mCancelList.contains(name)
    }

    fun getDesignSize(clazz: Class<Any>): Int {
        return clazz.canonicalName?.let { mDesignSizeMap[it] } ?: 0
    }


    fun cancel(resources: Resources) {
        DensityManager.cancel(resources)
    }

    fun adapt(resources: Resources, designSize: Int) {
        val metrics = Resources.getSystem().displayMetrics
        val isPortrait = Resources.getSystem() .configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        val screenSize = if (isPortrait) metrics.widthPixels else metrics.heightPixels
//        ScreenAdapt.log("adapt ==> designSize = $designSize, screenSize = $screenSize")
        DensityManager.adapt(resources, designSize, screenSize, 1 shl 3)
    }

    fun adapt(resources: Resources, designSize: Int, screenSize: Int) {
        DensityManager.adapt(resources, designSize, screenSize, 1 shl 4)
    }

    fun adapt(activity: Activity) {
        if (hasCancelAdapt && isCancelAdapt(activity.javaClass)) {
            cancel(activity.resources);
            return
        }
        if (hasDesignSizeAdapt) {
            val designSize = getDesignSize(activity.javaClass)
            if (designSize > 0) {
                adapt(activity.resources, designSize)
                return
            }
        }

        if (activity is CancelAdapt) {
            cancel(activity.resources)
            return
        }

        if (activity is DesignSizeAdapt) {
            adapt(activity.resources, activity.designSize)
            return
        }

        adapt(activity.resources, ScreenAdapt.designSize)
    }
}