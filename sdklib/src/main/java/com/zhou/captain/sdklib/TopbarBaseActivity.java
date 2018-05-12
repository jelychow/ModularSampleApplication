package com.zhou.captain.sdklib;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;

public abstract class TopbarBaseActivity extends SuperActivity {

    public String TAG = this.getClass().getSimpleName();
    QMUITopBar mTopBar;

    public abstract  void setLayoutId(@LayoutRes int layoutId);

    public int layoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getData();
        super.onCreate(savedInstanceState);
//        QMUIStatusBarHelper.translucent(this);
        setContentView(layoutId);
        initTopBar();
        initView();
    }




    public abstract void initData();

    public abstract void getData();

    public abstract void initView();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void initTopBar() {
        mTopBar = findViewById(R.id.topbar);
        mTopBar.setBackgroundColor(ContextCompat.getColor(this, R.color.qmui_config_color_blue));
        mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_still, R.anim.slide_out_right);
            }
        });

        mTopBar.setTitle("沉浸式状态栏示例");
    }
}
