package com.neirx.testalarmclock.ui.main.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by Waide Shery on 23.06.19.
 */
public class DataChangedHandler<T> {
    private RecyclerView.Adapter adapter;

    public DataChangedHandler(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public void onDataChanged(List<T> currentList, List<T> newList) {
        if (currentList.size() == 0 && newList.size() > 0) {
            Timber.tag("TTag").d("1");
            currentList.clear();
            currentList.addAll(newList);
            adapter.notifyItemRangeInserted(0, newList.size());
        } else if (currentList.size() > 0 && newList.size() == 0) {
            Timber.tag("TTag").d("2");
            currentList.clear();
            currentList.addAll(newList);
            adapter.notifyItemRangeRemoved(0, newList.size());
        } else if (currentList.size() == newList.size()) {
            Timber.tag("TTag").d("3");
            compareAndUpdate(currentList, newList);
        } else {
            Timber.tag("TTag").d("4");
            currentList.clear();
            currentList.addAll(newList);
            adapter.notifyDataSetChanged();
        }
    }

    private void compareAndUpdate(List<T> currentList, List<T> newList) {
        Disposable disposable = Single.fromCallable(() -> {
            List<Integer> changed = new ArrayList<>();
            for (int i = 0; i < currentList.size(); i++) {
                T first = currentList.get(i);
                T second = newList.get(i);
                if (!first.equals(second)) changed.add(i);
            }
            currentList.clear();
            currentList.addAll(newList);
            return changed;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(changed -> {
                    for (int i : changed){
                        adapter.notifyItemChanged(i);
                    }
                });
    }
}
