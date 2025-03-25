
package com.demo.app

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.reezy.cosmo.screenadapt.ScreenAdapt

//实现 CancelAdapt 即可取消当前 Activity 的屏幕适配, 并且这个 Activity 下的所有 Fragment 和 View 都会被取消适配
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun stop(view: View?) {
        ScreenAdapt.pause()
    }

    fun restart(view: View?) {
        ScreenAdapt.resume()
    }

    fun goCustomAdaptActivity(view: View?) {
        startActivity(Intent(applicationContext, DemoActivity::class.java))
//        startActivity(Intent(applicationContext, CustomAdaptActivity::class.java))
    }
}