package com.example.caowj.newtask.module1.model.impl;

import com.example.caowj.newtask.module1.Api.IndexService;
import com.example.caowj.newtask.module1.Api.Network;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ChoiceArticleList;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationList;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author : caowj
 * @Date : 2018/3/14
 */

public class IndexModelImpl extends BaseModelImpl<BaseDataBridge.IndexDataBridge> implements BaseModel.IndexModel {
    public IndexModelImpl(BaseDataBridge.IndexDataBridge modelImpl) {
        super(modelImpl);
    }

    @Override
    public void netWork() {

    }

    @Override
    public void getMoreInfoM(int pageIndex) {
        IndexService apiService = Network.getIndexService();
        Observable<ChoiceArticleList> observable = apiService.GetListWenZhang(3, pageIndex, WSConstants.WEB_SERVER_TOKEN);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChoiceArticleList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(ChoiceArticleList adInfoList) {
//                        LogUtil.myD(mTag + "2onNext..." + adInfoList.getCode());
                        List<Object> objectList = new ArrayList<>();
                        objectList.addAll(adInfoList.getData());

                        modelImpl.showMoreInfoB(objectList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.myE("onError..." + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.myD("onComplete...");
                    }
                });
    }

    @Override
    public void getFixedInfoM() {
        test2();
    }

    @Override
    public void getAdInfoM() {
        test1();
    }

    private void test2() {

        IndexService apiService = Network.getIndexService();
        Observable<ScrollNotificationList> observable1 = apiService.GetNotificationList(WSConstants.WEB_SERVER_TOKEN);
        Observable<ChoiceArticleList> observable2 = apiService.GetListWenZhang(3, 1, WSConstants.WEB_SERVER_TOKEN);
        Observable<ADInfoList> observable3 = apiService.GetAdList(WSConstants.WEB_SERVER_TOKEN);

        Observable.zip(observable1, observable2, observable3, new Function3<ScrollNotificationList, ChoiceArticleList, ADInfoList, List<Object>>() {

            @Override
            public List<Object> apply(ScrollNotificationList notificationList, ChoiceArticleList choiceArticleList, ADInfoList adInfoList) throws Exception {
                List<Object> dataList = new ArrayList<>();

                LogUtil.myD(mTag + "消息：" + notificationList.getCode() + ",轮播：" + adInfoList.getCode() + ",文章：" + choiceArticleList.getCode());

                dataList.add(adInfoList);
                dataList.add(notificationList);
                dataList.addAll(choiceArticleList.getData());
                return dataList;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Object>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(List<Object> objects) {
                        modelImpl.showFixedInfoB(objects);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.myE("onError..." + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.myD("onComplete...");
                    }
                });

    }

    @Override
    public void getNotificationM() {
        IndexService apiService = Network.getIndexService();
        Observable<ScrollNotificationList> call = apiService.GetNotificationList(WSConstants.WEB_SERVER_TOKEN);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScrollNotificationList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(ScrollNotificationList adInfoList) {
//                        LogUtil.myD(mTag + "2onNext..." + adInfoList.getCode());

                        modelImpl.showNotificationB(adInfoList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.myE("onError..." + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.myD("onComplete...");
                    }
                });
    }


    /**
     * Rxjava2+Retrofit2基本用法
     */
    private void test1() {
        IndexService apiService = Network.getIndexService();
        Observable<ADInfoList> call = apiService.GetAdList(WSConstants.WEB_SERVER_TOKEN);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ADInfoList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(ADInfoList adInfoList) {
//                        LogUtil.myD(mTag + "2onNext..." + adInfoList.getCode());

                        modelImpl.showAdInfoListB(adInfoList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.myE("onError..." + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.myD("onComplete...");
                    }
                });
    }
}
