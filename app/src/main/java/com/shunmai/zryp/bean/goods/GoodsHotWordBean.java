package com.shunmai.zryp.bean.goods;

import com.ysy.commonlib.base.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/28.
 */

public class GoodsHotWordBean extends TResponse<List<String>> {

    public GoodsHotWordBean(){
    }
    public GoodsHotWordBean(List<String> data){
        setData(data);
    }
}
