package com.bxjr.supplychain.test

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.zhou.captain.sdklib.rx.GankBeautyResult
import com.zhou.captain.sdklib.rx.netWork.ApiResponse


/**
 * Created by zhouzheng on 2017/11/28.
 * Email jelychow@gmail.com
 */

class GankViewModel : AndroidViewModel {

     var repository: GankRepository?=null

    constructor(application: Application) : super(application)

    constructor(mApplication: Application, mRepository: GankRepository) : super(mApplication) {
        repository = mRepository
    }


    fun getBeauties(i: Int, j: Int): LiveData<ApiResponse<GankBeautyResult>> {

        return repository!!.getBeauties(i, j)

    }


    class Factory(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {


        private var mRepository: GankRepository?=null

        init {
            mRepository = GankRepository.instance
        }

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GankViewModel(mApplication, mRepository!!) as T
        }
    }
}
