
package com.demo.app

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.reezy.cosmo.screenadapt.ScreenAdapt

//实现 CancelAdapt 即可取消当前 Activity 的屏幕适配, 并且这个 Activity 下的所有 Fragment 和 View 都会被取消适配
//public class MainActivity extends AppCompatActivity implements CancelAdapt {
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
        startActivity(Intent(applicationContext, CustomAdaptActivity::class.java))
    }
}