package com.zhou.captain.sdklib.rx.netWork;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zhou.captain.sdklib.rx.FastJsonConverterFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by zk on 2015/12/16.
 */
public class RetrofitSingleton2 {

//    private static ApiInterface apiService = null;

    private static ZhuangbiApi liveService = null;


    private static Retrofit retrofit = null;

    private static OkHttpClient okHttpClient = null;

    private static final String TAG = RetrofitSingleton2.class.getSimpleName();

//    public static Context context;

    /**
     * 初始化
     */

    public static void init() {

        Executor executor = Executors.newCachedThreadPool();

//        Gson gson = new GsonBuilder().create();
        // https://drakeet.me/retrofit-2-0-okhttp-3-0-config
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.zhuangbi.info/")
                .client(okHttpClient)
//                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .callbackExecutor(executor)
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();

//        apiService = retrofit.create(ApiInterface.class);
        liveService = retrofit.create(ZhuangbiApi.class);
    }

//    public static ApiInterface getApiService( ) {
//        if (apiService != null) {
//            return apiService;
//        } else {
//            init();
//            return getApiService();
//        }
//
//    }

//    public static LiveDataInterface getLiveDataService(Context context) {
//        if (liveService != null) {
//            return liveService;
//        } else {
//            init();
//            return getLiveDataService(context);
//        }
//    }

    public static ZhuangbiApi getLiveDataService() {
        if (liveService != null) {
            return liveService;
        } else {
            init();
            return getLiveDataService();
        }
    }

    public static Retrofit getRetrofit(Context context) {
        if (retrofit != null) {
            return retrofit;
        }
        init();
        return getRetrofit(context);
    }

    public static OkHttpClient getOkHttpClient( ) {
        if (okHttpClient != null) {
            return okHttpClient;
        }
        init();
        return getOkHttpClient();
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
