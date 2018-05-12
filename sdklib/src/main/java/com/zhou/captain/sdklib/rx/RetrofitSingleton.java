package com.zhou.captain.sdklib.rx;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhou.captain.sdklib.rx.netWork.LiveDataCallAdapterFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Created by zk on 2015/12/16.
 */
public class RetrofitSingleton {

    private static ZhuangbiApi2 apiService = null;

//    private static LiveData liveService = null;

    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient = null;

    private static final String TAG = RetrofitSingleton.class.getSimpleName();

//    public static Context context;

    /**
     * 初始化
     */

    public static void init(final Context context) {

        Executor executor = Executors.newCachedThreadPool();


        okHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(okHttpClient)
//                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .callbackExecutor(executor)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ZhuangbiApi2.class);
//        liveService = retrofit.create(LiveData.class);
    }

    public static ZhuangbiApi2 getApiService(Context context) {
        if (apiService != null) {
            return apiService;
        } else {
            init(context);
            return getApiService(context);
        }


    }
//
//    public static LiveData getLiveDataService(Context context) {
//        if (liveService != null) {
//            return liveService;
//        } else {
//            init(context);
//            return getLiveDataService(context);
//        }
//
//
//    }

    public static Retrofit getRetrofit(Context context) {
        if (retrofit != null) {
            return retrofit;
        }
        init(context);
        return getRetrofit(context);
    }

    public static OkHttpClient getOkHttpClient(Context context) {
        if (okHttpClient != null) {
            return okHttpClient;
        }
        init(context);
        return getOkHttpClient(context);
    }

    public static void disposeFailureInfo(Throwable t, Context context, View view) {
        if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
                t.toString().contains("UnknownHostException")) {
            Snackbar.make(view, "网络不好,~( ´•︵•` )~", Snackbar.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
        }
        Log.w(TAG, t.toString());
    }
}
