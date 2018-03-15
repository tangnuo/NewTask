package com.example.caowj.newtask.module1.model.impl;

import com.example.caowj.newtask.module1.Api.IndexService;
import com.example.caowj.newtask.module1.Api.Network;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;
import com.example.caowj.newtask.utils.LogUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
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
    public void getAdInfoM() {

        test1();
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
