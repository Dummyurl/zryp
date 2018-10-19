package com.shunmai.zryp.ui.goods;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.R;

public class GoodsOrderActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);
        initWindow(this);
    }
}
