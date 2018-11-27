package com.ysy.commonlib.base;

import android.content.Intent;
import android.view.View;

/**
 * Created by yushengyang.
 * Date: 2018/10/19.
 */

public class BaseEventHandler {
    public void clickToActivity(View view, String clz) {
        Intent intent = null;
        try {
            intent = new Intent(view.getContext(), Class.forName(clz));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        view.getContext().startActivity(intent);
    }
}
