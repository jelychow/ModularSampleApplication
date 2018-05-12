package com.zhou.captain.modularsampleapplication

import android.Manifest
import android.os.Bundle
import android.util.Log
import com.tbruyelle.rxpermissions2.RxPermissions
import com.zhou.captain.sdklib.TopbarBaseActivity
import com.zhou.captain.sdklib.ccg.IGoToImpl
import com.zhou.captain.uilib.toolView.AppSettingsDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : TopbarBaseActivity() {
    override fun setLayoutId(layoutId: Int) {
        this.layoutId = layoutId
    }

    override fun initData() {
        Log.d(TAG, "initData")
    }

    override fun getData() {
        Log.d(TAG, "getData")
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setLayoutId(R.layout.activity_main)
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        tv_hint?.setOnClickListener {

            IGoToImpl.Builder()
                    .setPath("/login/login")
                    .justStart()
        }

        tv_click_me?.setOnClickListener {

          goPic()
        }

        tv_click_3.onClick {
            IGoToImpl.Builder()
                    .setPath("/moduleB/test")
                    .justStart()
        }

    }

    fun goPic() {
        val rxPermissions = RxPermissions(this) // where this is an Activity instance

        rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    run {
                        if (granted) {

                            IGoToImpl.Builder().setPath("picture").setRequestCode(1001).setActivity(this@MainActivity).navagation()

                        } else {
                            AppSettingsDialog.Builder(this).build().show()
                        }
                    }
                }

    }
}
