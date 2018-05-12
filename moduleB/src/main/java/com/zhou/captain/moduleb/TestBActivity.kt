package com.zhou.captain.moduleb

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.fastjson.JSON
import com.bxjr.supplychain.test.GankViewModel
import com.bxjr.supplychain.test.ZhuangbiListAdapter
import com.zhou.captain.loginlib.R
import com.zhou.captain.sdklib.TopbarBaseActivity
import kotlinx.android.synthetic.main.activity_test_b.*
@Route(path = "/moduleB/test")
class TestBActivity : TopbarBaseActivity() {

    internal var adapter = ZhuangbiListAdapter()


    override fun setLayoutId(layoutId: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        layoutId = R.layout.activity_test_b
        super.onCreate(savedInstanceState)
    }

    override fun initData() {

    }

    override fun getData() {
        var model = GankViewModel.Factory(application).create(GankViewModel::class.java)
        model.getBeauties(10, 1).observe(this, android.arch.lifecycle.Observer { res ->
            run {

                var result = res?.body;
                Log.d(TAG, JSON.toJSONString(res))


                result?.results?.let {
                    adapter.setImages(it)

                    Log.d(TAG, JSON.toJSONString(it))

                }

            }
        })
    }

    override fun initView() {
        rv_pics?.apply {

            adapter = this@TestBActivity.adapter
            layoutManager = GridLayoutManager(this@TestBActivity, 3)
        }
    }
}
