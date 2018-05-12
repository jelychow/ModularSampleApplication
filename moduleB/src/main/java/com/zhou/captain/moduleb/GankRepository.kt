/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bxjr.supplychain.test

import android.arch.lifecycle.LiveData
import com.zhou.captain.sdklib.BaseApp
import com.zhou.captain.sdklib.rx.GankBeautyResult
import com.zhou.captain.sdklib.rx.RetrofitSingleton
import com.zhou.captain.sdklib.rx.netWork.ApiResponse


/**
 * Repository that handles User objects.
 */
class GankRepository private constructor() {

    fun getBeauties(i: Int, j: Int): LiveData<ApiResponse<GankBeautyResult>> {

        return RetrofitSingleton.getApiService(BaseApp.getInstance()).getBeauties(i, j)

    }

    companion object {
        private  var sInstance: GankRepository? = null

        val instance: GankRepository?
            get() {
                if (sInstance == null) {
                        if (sInstance == null) {
                            sInstance = GankRepository()
                        }

                }
                return sInstance
            }
    }


}
