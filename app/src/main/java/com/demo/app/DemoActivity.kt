
package com.demo.app

import androidx.appcompat.app.AppCompatActivity
import me.reezy.cosmo.screenadapt.adapt.CancelAdapt
import me.reezy.cosmo.screenadapt.adapt.DesignSizeAdapt

class DemoActivity() : AppCompatActivity(), DesignSizeAdapt {

    override val designSize: Int = 400
}