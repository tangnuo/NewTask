package com.example.caowj.newtask.module1.model.impl;

import com.example.caowj.newtask.module1.model.BaseModel;
import com.example.caowj.newtask.module1.presenter.BaseDataBridge;

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


//        modelImpl.showAdInfoListB();
    }
}
