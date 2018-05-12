package debug;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.zhou.captain.sdklib.BaseApp;


/**
 * Created by zhou on 2018/4/25.
 */

public class ModuleLoginApp extends BaseApp {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
