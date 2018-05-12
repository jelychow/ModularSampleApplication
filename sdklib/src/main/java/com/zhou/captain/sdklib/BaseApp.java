package com.zhou.captain.sdklib;

import android.app.Activity;
import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

/**
 * Created by zhouzheng on 2018/2/26.
 */

public class BaseApp extends Application {

    private static BaseApp app;

    private List<WeakReference<SuperActivity>> activityList = new LinkedList<>();
    private WeakReference<SuperActivity> currActivity;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initHttp();
        Utils.init(this);

    }


    public static BaseApp getInstance() {
        if (null == app) {
            app = new BaseApp();
        }
        return app;
    }

    public WeakReference<SuperActivity> getCurrActivity() {
        return currActivity;
    }

    public void setCurrActivity(WeakReference<SuperActivity> currActivity) {
        this.currActivity = currActivity;
    }

    public void addActivity(SuperActivity activity) {
        this.activityList.add(new WeakReference<>(activity));
    }

    public void removeActivity(Activity activity) {

        int index = -1;

        for (int i = 0; i < activityList.size(); i++) {

            if (activity.equals(activityList.get(i).get())) {
                index = i;
            }
        }

        if (index != -1) {
            activityList.remove(index);
        }

    }

    public void removeAllActivity(boolean flag) {
        for (WeakReference<SuperActivity> act : activityList) {
            if (!flag) {
                if (act.get().equals(currActivity)) {
                    continue;
                }
            }
            act.get().finish();
        }
    }

    /**
     * 移除所有activity
     * 除了 架构 MainActivity  LicaishiActivity
     *
     * @param flag
     */
    public void removeAllActivityNotMain(boolean flag) {
        for (WeakReference<SuperActivity> act : activityList) {
            if (!flag) {
                if (act.get().equals(currActivity.get())) {
                    continue;
                }

                if (act.get().getLocalClassName().contains("NewMainActivity")) {
                    continue;
                }

                if (act.get().getLocalClassName().contains("LicaishiActivity")) {
                    continue;
                }

            }
            act.get().finish();
        }
    }






    public void initHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));

        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();

        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        //builder.hostnameVerifier(new SafeHostnameVerifier());

        OkGo.getInstance().init(app)
                //必须调用初始化
                .setOkHttpClient(builder.build())
                //建议设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)
                //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3);
//                //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)
//                //全局公共头
//                .addCommonParams(params);
    }

}
