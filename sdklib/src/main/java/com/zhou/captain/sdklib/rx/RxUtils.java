package com.zhou.captain.sdklib.rx;


import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;

public class RxUtils {

    private RxUtils() {
    }

    public static void unsubscribeIfNotNull(Disposable subscription) {
        if (subscription != null&&!subscription.isDisposed()) {
            subscription.dispose();
        }
    }

}
