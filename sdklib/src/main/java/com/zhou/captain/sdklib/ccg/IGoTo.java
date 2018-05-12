package com.zhou.captain.sdklib.ccg;

import android.app.Activity;
import android.os.Bundle;

import java.util.HashMap;

/**
 * the name ccg is an abbreviation of china center government
 * the main function is control modular routers
 * @author zhou
 * @date 2018-05-7
 *
 */
public interface IGoTo {


    public void justStart(String path) ;
    /**
     * @author zhou
     * @param activity
     * @param cls
     * @desc  just normal start activity
     */
    public void startModular(Activity activity, Class<?> cls);

    /**
     * @author zhou
     * @param activity
     * @param cls
     * @param requestCode
     * @desc   just like start activity for result
     */
    public void startModularForResult(Activity activity, Class<?> cls, int requestCode);


    /**
     * @author zhou
     * @param activity
     * @param cls
     * @param requestCode
     * @param map
     * @desc   just like start activity for result with params
     */
    public void startModularForResultWithParam(Activity activity, Class<?> cls, int requestCode, HashMap map);


    /**
     * @author zhou
     * @param activity
     * @param path
     * @param requestCode
     * @param map
     * @desc   just like start activity for result with params
     */
    public void startModularForResultWithParam(Activity activity, String path, int requestCode, Bundle map);

    /**
     * @author zhou
     * @param activity
     * @param r
     * @des start camera module
     */
    public void startPictureModular(Activity activity, int r);
}
