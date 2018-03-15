package com.example.caowj.newtask.module1.ItemViewBinder;

import com.example.caowj.newtask.module1.entity.CommonBean;
import com.example.caowj.newtask.module1.entity.bean.AdBean;

import java.util.List;

/**
 * 图片轮播
 * package: com.jsfengling.qipai.data
 * className: AdBean
 *
 * @author caowj
 *         date：2015年10月12日 下午2:18:58
 */
public class ADInfoList extends CommonBean {

    private List<AdBean> data;

    public List<AdBean> getData() {
        return data;
    }

    public void setData(List<AdBean> data) {
        this.data = data;
    }
}
