package com.zhou.captain.sdklib.ccg;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.HashMap;

public class IGoToImpl implements IGoTo {

    public static class Builder {
        IGoToImpl ccg;

        private Activity activity;
        private int requestCode = -1;
        private Bundle params;
        private String path;

        public String getPath() {
            return path;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Activity getActivity() {
            return activity;
        }

        public Builder setActivity(Activity activity) {
            this.activity = activity;

            return this;
        }

        public int getRequestCode() {
            return requestCode;
        }

        public Builder setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;

        }

        public Bundle getParams() {
            return params;
        }

        public Builder setParams(Bundle params) {
            this.params = params;
            return this;
        }

        public Builder() {
            synchronized (IGoToImpl.class) {
                if (ccg == null) {
                    ccg = new IGoToImpl();
                }
                ccg.pure();
            }
        }

        public IGoToImpl create() {
            if (TextUtils.isEmpty(getPath())) {
                try {
                    throw new Exception("path can not be null");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return ccg;
        }

        public void navagation() {

            create();

            switch (getPath()) {

                case "picture":

                    ccg.startPictureModular(getActivity(), getRequestCode());

                    break;
                default:

                    ccg.startModularForResultWithParam(getActivity(), getPath(), getRequestCode(), getParams());
                    return;

            }

        }

        public void justStart() {
            create();
            ccg.justStart(getPath());
        }
    }

    private void pure() {

    }

    @Override
    public void justStart(String path) {
        ARouter.getInstance().build(path)
                .navigation();
    }

    @Override
    public void startModular(Activity activity, Class<?> cls) {

        ARouter.getInstance().build("/moduleb/main")

                .navigation();
    }

    @Override
    public void startModularForResult(Activity activity, Class<?> cls, int requestCode) {

    }

    @Override
    public void startModularForResultWithParam(Activity activity, Class<?> cls, int requestCode, HashMap map) {


    }

    @Override
    public void startModularForResultWithParam(Activity activity, String path, int requestCode, Bundle map) {
        Postcard aRouter = ARouter.getInstance().build(path);

        if (map != null) {
            aRouter.with(map);
        }
        aRouter.navigation(activity, requestCode);
    }

    @Override
    public void startPictureModular(@NonNull Activity activity, @NonNull int r) {
        Matisse.from(activity)
                .choose(MimeType.allOf())
                .capture(true)
                .captureStrategy(new CaptureStrategy(true, "com.zhou.captain.modularsampleapplication.fileprovider"))
                .countable(true)
                .maxSelectable(9)
//                .gridExpectedSize(activity.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(r);

    }
}
