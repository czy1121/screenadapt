package me.reezy.cosmo.screenadapt.internal

import android.app.Application
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.LongSparseArray
import androidx.annotation.MainThread
import me.reezy.cosmo.screenadapt.ScreenAdapt
import me.reezy.cosmo.screenadapt.Subunit

internal object DensityManager {
    private var application: Application? = null

    private var systemDensityInfo: DensityInfo = DensityInfo()

    private val cache = LongSparseArray<DensityInfo>()

    var isSupportSP: Boolean = true
    var isSupportDP: Boolean = true
    var subunit: Subunit = Subunit.NONE


    /** 字体大小的缩放比例，允许 APP 全局调节使用 sp 为单位的字体大小，值设为 0 取消此功能 */
    var fontScale: Float = 0f

    /** 是否屏蔽系统字体大小的影响，如果为 true，APP 内的字体大小将不会跟随系统设置中字体大小的改变 */
    var isExcludeSystemFontScale: Boolean = false

    fun init(app: Application) {
        application = app

        val dm = Resources.getSystem().displayMetrics
        systemDensityInfo = DensityInfo(dm.density, dm.densityDpi, dm.scaledDensity, dm.xdpi)
    }

    fun setSystemScaledDensity(value: Float) {
        systemDensityInfo = systemDensityInfo.copy(scaledDensity = value)
    }

    fun cancel(resources: Resources) {
        val xdpi = when (subunit) {
            Subunit.PT -> systemDensityInfo.xdpi / 72f
            Subunit.MM -> systemDensityInfo.xdpi / 25.4f
            else -> systemDensityInfo.xdpi
        }
        adapt(resources, systemDensityInfo.copy(xdpi = xdpi))
    }

    fun adapt(resources: Resources, designSize: Int, screenSize: Int, flags: Int) {
        val key = ((systemDensityInfo.scaledDensity * 1000).toLong() shl 48) or (designSize.toLong() shl 32) or (screenSize.toLong() shl 16) or flags.toLong()

        val info = cache.get(key) ?: kotlin.run {
            val density = screenSize / designSize.toFloat()
            val densityDpi = (density * 160).toInt()
            val scaledDensity = density * when {
                fontScale > 0f -> fontScale
                isExcludeSystemFontScale -> 1f
                else -> systemDensityInfo.scaledDensity / systemDensityInfo.density
            }
            val info =  DensityInfo(density, densityDpi, scaledDensity, density)
            cache.put(key, info)
            info
        }
        adapt(resources, info)
    }

    @MainThread
    private fun adapt(resources: Resources, info: DensityInfo) {
        adapt(resources.displayMetrics, info)
        adapt(application?.resources?.displayMetrics ?: return, info)
    }


    private fun adapt(dm: DisplayMetrics, info: DensityInfo) {
        if (isSupportDP) {
            dm.density = info.density
            dm.densityDpi = info.densityDpi
        }
        if (isSupportSP) {
            dm.scaledDensity = info.scaledDensity
        }
        when (subunit) {
            Subunit.NONE -> {}
            Subunit.PT -> dm.xdpi = info.xdpi * 72f
            Subunit.IN -> dm.xdpi = info.xdpi
            Subunit.MM -> dm.xdpi = info.xdpi * 25.4f
        }
    }
}