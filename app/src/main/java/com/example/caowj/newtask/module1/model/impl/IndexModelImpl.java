package com.example.caowj.newtask.module1.model.impl;

import com.example.caowj.newtask.module1.Api.IndexService;
import com.example.caowj.newtask.module1.Api.Network;
import com.example.caowj.newtask.module1.ItemViewBinder.ADInfoList;
import com.example.caowj.newtask.module1.ItemViewBinder.ChoiceArticleList;
import com.example.caowj.newtask.module1.ItemViewBinder.ScrollNotificationList;
import com.example.caowj.newtask.module1.constants.WSConstants;
import com.example.caowj.newtask.module1.entity.PaiPinInfoList;
import com.example.caowj.newtask.module1.entity.SysPicInfoList;
import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function4;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author : caowj
 * @Date : 2018/3/14
 */

public class IndexModelImpl extends BaseModelImpl<BaseDataBridge.IndexDataBridge> implements BaseModel.IndexModel {
    private List<Object> listChange1;
    private List<Object> listChange2;

    public IndexModelImpl(BaseDataBridge.IndexDataBridge modelImpl) {
        super(modelImpl);
        listChange1 = new ArrayList<>();
        listChange2 = new ArrayList<>();
    }


    @Override
    public void netWork() {

    }

    @Override
    public void getMoreInfoM2(int pageIndex) {
        IndexService apiService = Network.getIndexService();

        Observable<ChoiceArticleList> observable1 = apiService.GetListWenZhang(3, pageIndex, WSConstants.WEB_SERVER_TOKEN);
        Observable<ChoiceArticleList> observable2 = apiService.GetListWangQiWenZhang(3, pageIndex, WSConstants.WEB_SERVER_TOKEN);

        Observable.zip(observable1, observable2, new BiFunction<ChoiceArticleList, ChoiceArticleList, List<Object>>() {
            @Override
            public List<Object> apply(ChoiceArticleList choiceArticleList, ChoiceArticleList choiceArticleList2) throws Exception {
                List<Object> dataList = new ArrayList<>();
                listChange1.addAll(choiceArticleList.getData());
                listChange2.addAll(choiceArticleList2.getData());
                dataList.addAll(listChange1);
                dataList.addAll(listChange2);
                LogUtil.myD("size1:" + listChange1.size() + ",size2:" + listChange2.size());
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
                        modelImpl.showMoreInfoB(objects);
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
        testZip();

        //测试代码
        getImageDetail();
    }

    @Override
    public void getAdInfoM() {
        test1();
    }


    /**
     * 多接口请求（zip（））
     */
    private void testZip() {

        IndexService apiService = Network.getIndexService();
        Observable<ScrollNotificationList> observable1 = apiService.GetNotificationList(WSConstants.WEB_SERVER_TOKEN);
        Observable<ChoiceArticleList> observable2 = apiService.GetListWenZhang(3, 1, WSConstants.WEB_SERVER_TOKEN);
        Observable<ADInfoList> observable3 = apiService.GetAdList(WSConstants.WEB_SERVER_TOKEN);
        Observable<ChoiceArticleList> observable4 = apiService.GetListWangQiWenZhang(3, 1, WSConstants.WEB_SERVER_TOKEN);

        Observable.zip(observable1, observable2, observable3, observable4, new Function4<ScrollNotificationList, ChoiceArticleList, ADInfoList, ChoiceArticleList, List<Object>>() {

            @Override
            public List<Object> apply(ScrollNotificationList notificationList, ChoiceArticleList choiceArticleList, ADInfoList adInfoList, ChoiceArticleList choiceArticleList2) throws Exception {
                List<Object> dataList = new ArrayList<>();
                listChange1 = new ArrayList<>();
                listChange2 = new ArrayList<>();

                LogUtil.myD(mTag + "消息：" + notificationList.getCode() + ",轮播：" + adInfoList.getCode() + ",精选文章：" + choiceArticleList.getCode() + ",往期文章：" + choiceArticleList2.getCode());


                listChange1.add(adInfoList);
                listChange1.add(notificationList);
                listChange1.addAll(choiceArticleList.getData());

                listChange2.addAll(choiceArticleList2.getData());

                dataList.addAll(listChange1);
                dataList.addAll(listChange2);
                LogUtil.myD("size1:" + listChange1.size() + ",size2:" + listChange2.size());
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


    /**
     * 连接两个或多个同类型数据（concat（））。
     */
    private void testConcat() {
        IndexService apiService = Network.getIndexService();
        Observable<ChoiceArticleList> observable2 = apiService.GetListWenZhang(3, 1, WSConstants.WEB_SERVER_TOKEN);
        Observable<ChoiceArticleList> observable4 = apiService.GetListWangQiWenZhang(3, 1, WSConstants.WEB_SERVER_TOKEN);
        Observable.concat(observable2, observable4).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChoiceArticleList>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.myD("onSubscribe...");
                    }

                    @Override
                    public void onNext(ChoiceArticleList choiceArticleList) {
                        LogUtil.myD(mTag + "code:" + choiceArticleList.getCode());
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

    /**
     * 获取图片详情案例（多个网络请求依次依赖：flatMap（））
     * <p>
     * 先获取图片详情，再根据串码获取真实图片路径。
     * </p>
     */
    private void getImageDetail() {
        final IndexService apiService = Network.getIndexService();
        Observable<PaiPinInfoList> call = apiService.GetPaiPiID(23645, WSConstants.WEB_SERVER_TOKEN);

        Observable<SysPicInfoList> observable = call.flatMap(new Function<PaiPinInfoList, ObservableSource<SysPicInfoList>>() {
            @Override
            public ObservableSource<SysPicInfoList> apply(PaiPinInfoList paiPinInfoList) throws Exception {
                String picN = paiPinInfoList.getData().get(0).getPic();
                LogUtil.myD(mTag + "code:" + paiPinInfoList.getCode() + "，图片串码：" + picN);
                return apiService.GetPICNO(picN, WSConstants.WEB_SERVER_TOKEN);
            }
        });

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SysPicInfoList>() {
                    @Override
                    public void accept(@NonNull SysPicInfoList sysPicInfoList) throws Exception {
                        LogUtil.myD(mTag + "code:" + sysPicInfoList.getCode() + "，图片详情：" + sysPicInfoList.getData().size());
                    }
                });
    }
}
