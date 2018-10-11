package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.repository.CategoryRepository;
import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.network.onResponseFailedListener;


/**
 * Created by yushengyang.
 * Date: 2018/9/25.
 */

public class SearchFragmentViewModel extends ViewModel {
//    private MutableLiveData<CategoryBean> categoryBean;
    private CategoryRepository repository;

    public SearchFragmentViewModel() {

    }
    public void init(CategoryRepository repository){
        this.repository = repository;
    }
    public LiveData<CategoryBean> getCategoryBean( onResponseFailedListener listener) {
        return repository.getCategory(false, listener);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestory();
    }
}
