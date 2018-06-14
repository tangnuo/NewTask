package com.example.caowj.newtask.mvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.example.caowj.newtask.mvvm.data.Special;
import com.example.caowj.newtask.mvvm.db.entity.SpecialListEntity;
import com.example.caowj.newtask.mvvm.network.EmptyConsumer;
import com.example.caowj.newtask.mvvm.network.ErrorConsumer;
import com.example.caowj.newtask.mvvm.network.HttpResult;
import com.example.caowj.newtask.mvvm.source.AwakerRepository;
import com.kedacom.base.mvvm.viewmodel.BaseListViewModel;
import com.kedacom.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 使用LiveData刷新数据。
 * Created by ruzhan on 2017/7/6.
 */

public class VideoViewModel extends BaseListViewModel {

    private static final String TAG = "VideoViewModel";

    private int cat = Special.NORMAL;
    private RefreshListModel<Special> refreshListModel = new RefreshListModel<>();

    private List<Special> specialList = new ArrayList<>();
    private MutableLiveData<RefreshListModel<Special>> specialLiveData = new MutableLiveData<>();

    private Map<String, List<Special>> stringListMap = new HashMap<>();

    private Disposable localDisposable;

    public VideoViewModel() {
        specialLiveData.setValue(null);
    }

    public void loadSpecialListEntity(String cat) {
        localDisposable = AwakerRepository.get().loadSpecialListEntity(cat)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> LogUtil.d(TAG,
                        "initLocalSpecialListEntity doOnError: " + throwable.toString()))
                .doOnNext(specialListEntity -> {
                    if (specialListEntity.specialList != null && !stringListMap.containsKey(cat)) {
                        refreshListModel.setRefreshType(specialListEntity.specialList);
                        specialLiveData.setValue(refreshListModel);
                    }
                    localDisposable.dispose();
                })
                .subscribe(new EmptyConsumer(), new ErrorConsumer());
    }

    @Override
    public void refreshData(boolean refresh) {
        AwakerRepository.get().getSpecialList(TOKEN, page, cat)
                .map(HttpResult::getData)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> LogUtil.d(TAG,
                        "refreshData doOnError called..." + throwable.toString()))
                .doOnSubscribe(disposable -> isRunning.set(true))
                .doOnTerminate(() -> isRunning.set(false))
                .doOnNext(specials -> refreshDataOnNext(specials, refresh))
                .subscribe(new EmptyConsumer(), new ErrorConsumer());
    }

    /**
     * 刷新数据
     *
     * @param specials
     * @param refresh
     */
    private void refreshDataOnNext(List<Special> specials, boolean refresh) {
        if (refresh) {
            specialList.clear();
            refreshListModel.setRefreshType();
        } else {
            refreshListModel.setUpdateType();
        }

        specialList.addAll(specials);
        refreshListModel.setList(specialList);
        specialLiveData.setValue(refreshListModel);

        stringListMap.put(String.valueOf(cat), specialList);

        setSpecialListToLocalDb(specialList);
    }

    private void setSpecialListToLocalDb(List<Special> specials) {
        Flowable.create(e -> saveSpecialListToLocal(new ArrayList<>(specials), e),
                BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> LogUtil.d(TAG,
                        "setSpecialListToLocalDb doOnError: " + throwable.toString()))
                .doOnComplete(() -> LogUtil.d(TAG,
                        "setSpecialListToLocalDb doOnComplete called..."))
                .subscribe(new EmptyConsumer(), new ErrorConsumer());
    }

    /**
     * 缓存数据到本地
     *
     * @param specials
     * @param e
     */
    private void saveSpecialListToLocal(List<Special> specials, FlowableEmitter e) {
        if (specials != null) {
            SpecialListEntity specialListEntity = new SpecialListEntity(String.valueOf(cat), specials);
            AwakerRepository.get().insertAll(specialListEntity);
        }
        e.onComplete();
    }

    public MutableLiveData<RefreshListModel<Special>> getSpecialLiveData() {
        return specialLiveData;
    }
}
