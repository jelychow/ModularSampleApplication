// (c)2016 Flipboard Inc, All Rights Reserved.

package com.zhou.captain.sdklib.rx;


import android.arch.lifecycle.LiveData;

import com.zhou.captain.sdklib.rx.netWork.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhuangbiApi2 {
    @GET("data/福利/{number}/{page}")
    LiveData<ApiResponse<GankBeautyResult>> getBeauties(@Path("number") int number, @Path("page") int page);
}
