package com.zhou.captain.uilib.toolView;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.zhou.captain.uilib.R;


/**
 * ToastUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {


    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {
        show(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {

        try {
            if ( Build.VERSION.SDK_INT==Build.VERSION_CODES.N_MR1) {
                ToastCompatFor7 toast = ToastCompatFor7.makeText(context,text,duration);
                toast.show();
            } else {
                Toast toast = Toast.makeText(context.getApplicationContext(),text,duration);
                toast.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }

    public static void  showBackground(Context context) {

        try {
            if ( Build.VERSION.SDK_INT==Build.VERSION_CODES.N_MR1) {
                ToastCompatFor7 toast = new ToastCompatFor7(context.getApplicationContext());
                getToastView(context, toast);
            } else {
                Toast toast = new Toast(context.getApplicationContext());
                getToastView(context, toast);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void getToastView(Context context, Toast result) {
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.toast_background, null);
        result.setView(v);
        result.setGravity(Gravity.CENTER, 0, 0);
        result.setDuration(Toast.LENGTH_SHORT);
        result.show();
    }


}
