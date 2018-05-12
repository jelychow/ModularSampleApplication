package com.zhou.captain.sdklib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SuperActivity extends AppCompatActivity {

    public static int id = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_super);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
    }
}
