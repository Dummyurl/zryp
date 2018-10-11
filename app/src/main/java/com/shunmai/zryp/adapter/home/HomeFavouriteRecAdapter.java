package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.databinding.ObservableList;
import android.os.Looper;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by yushengyang.
 * Date: 2018/8/23.
 */

public class HomeFavouriteRecAdapter<T> extends CommonViewAdapter<T> {
    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);

    public HomeFavouriteRecAdapter(Context context, int layoutId,List<T> datas) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, T item) {
//        ((TextView) holder.getView(R.id.tv_sample)).setText(item);
    }


    static void ensureChangeOnMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
        }
    }
    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<HomeFavouriteRecAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(HomeFavouriteRecAdapter<T> adapter) {
            this.adapterRef = new WeakReference<>(adapter);
        }

        @Override
        public void onChanged(ObservableList sender) {
            HomeFavouriteRecAdapter<T> adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }
            ensureChangeOnMainThread();
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {
            onChanged(sender);
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {
            onChanged(sender);
        }
    }
}
