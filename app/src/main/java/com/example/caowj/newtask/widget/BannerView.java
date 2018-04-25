package com.example.caowj.newtask.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.PaiPinInfo;
import com.example.caowj.newtask.toutiao.util.GlideUtil;
import com.example.caowj.newtask.module1.entity.bean.AdBean;
import com.example.caowj.newtask.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告轮播图<br/>
 * 可以替换
 * 1、通过handler实现轮播效果
 * 2、在开头和结尾添加过度
 * 3、手势滑动时暂停定时器
 */
public class BannerView extends FrameLayout {
    private final String mTag = this.getClass().getSimpleName() + "~~~";
    private int count;
    private Context mContext;
    private ViewPager vp;
    private boolean isAutoPlay;
    private int currentItem;
    private int delayTime;
    /**
     * 定位圆点的布局
     */
    private LinearLayout ll_dot;
    /**
     * 广告图片集合
     */
    private List<ImageView> imageViews;
    /**
     * 定位圆点集合
     */
    private List<ImageView> iv_dots;
    private Handler handler = new Handler();
    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
                if (currentItem == 1) {
                    vp.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    vp.setCurrentItem(currentItem);
                    handler.postDelayed(task, delayTime);
                }
            } else {
                handler.postDelayed(task, delayTime);
            }
        }
    };
    /**
     * 外部布局
     */
    private View view;
    private PaiPinInfo paiPinInfo;
    private int registerCount = 0;
    private List<AdBean> adInfoList;

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        initData();
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context) {
        this(context, null);
    }

    private void initData() {
        imageViews = new ArrayList<ImageView>();
        iv_dots = new ArrayList<ImageView>();
    }

    /**
     * 设置广告数据
     *
     * @param adInfoList 广告数据
     * @param autoPlay   是否自动轮播
     * @param delayTime  延迟时间（毫秒）
     */
    public void setADInfo(List<AdBean> adInfoList, boolean autoPlay, int delayTime) {
        this.delayTime = delayTime;
        this.isAutoPlay = autoPlay;
        this.adInfoList = adInfoList;
        initLayout();
        initImgFromADInfo(adInfoList);
        showTime();
    }

    /**
     * 初始化布局
     */
    private void initLayout() {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_banner, this, true);
        }
        vp = (ViewPager) view.findViewById(R.id.vp);
        ll_dot = (LinearLayout) view.findViewById(R.id.ll_dot);
        ll_dot.removeAllViews();
        imageViews.clear();
        iv_dots.clear();
    }

    /**
     * 初始化图片
     *
     * @param adInfoList
     */
    private void initImgFromADInfo(List<AdBean> adInfoList) {
        if (adInfoList == null || adInfoList.size() == 0) {
            return;
        }
        count = adInfoList.size();
        for (int i = 0; i < count; i++) {
            ImageView iv_dot = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            iv_dot.setImageResource(R.drawable.dot_blur);
            ll_dot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }
        iv_dots.get(0).setImageResource(R.drawable.dot_focus);

        if (count > 1) {
            for (int i = 0; i <= count + 1; i++) {
                ImageView iv = new ImageView(mContext);
                iv.setScaleType(ScaleType.CENTER_CROP);
                AdBean adInfo = null;
                if (i == 0) {
                    adInfo = adInfoList.get(count - 1);
                } else if (i == count + 1) {
                    adInfo = adInfoList.get(0);
                } else {
                    adInfo = adInfoList.get(i - 1);
                }
                GlideUtil.loadNormal(mContext, adInfo.getImages(), iv);
//                GlideUtils.loadStringRes(iv, AlimmdnUtil.modifyImagePath(adInfo.getImages()), GlideUtils.setConfigSize(adInfo.getImages(), 750, 500), null);
//                LogUtil.d(mTag, "当前轮播图信息：" + AlimmdnUtil.modifyImagePath(adInfo.getImages()));
                imageViews.add(iv);
            }
        } else if (count == 1) {
            //一张图片时不轮播
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ScaleType.CENTER_CROP);
            AdBean adInfo = adInfoList.get(count - 1);
            GlideUtil.loadNormal(mContext, adInfo.getImages(), iv);
//            GlideUtils.loadStringRes(iv, AlimmdnUtil.modifyImagePath(adInfo.getImages()), GlideUtils.setConfigSize(adInfo.getImages(), 750, 500), null);
//            LogUtil.d(mTag, "当前轮播图信息：" + AlimmdnUtil.modifyImagePath(adInfo.getImages()));
            imageViews.add(iv);
        } else {
            LogUtil.myW("没有轮播图片");
        }

    }

    private void showTime() {
        vp.setAdapter(new BannerPagerAdapter());
        vp.setFocusable(true);
        vp.setCurrentItem(1);
        currentItem = 1;
        vp.addOnPageChangeListener(new MyOnPageChangeListener());
        if (isAutoPlay) {
            startPlay();
        }
    }

    private void startPlay() {
        handler.removeCallbacks(task);
        handler.postDelayed(task, delayTime);
    }

    public void stopPlay() {
        if (handler != null) {
            handler.removeCallbacks(task);
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (imageViews != null) {
            imageViews.clear();
            imageViews = null;
        }
        if (iv_dots != null) {
            iv_dots.clear();
            iv_dots = null;
        }
        if (adInfoList != null) {
            adInfoList.clear();
            adInfoList = null;
        }
        if (paiPinInfo != null) {
            paiPinInfo = null;
        }
        registerCount = 0;
//        GlideUtils.cleanAll(mContext);
    }

    private class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);

            final AdBean info;
            if (adInfoList.size() > 0) {
                if (position == 0) {
                    info = adInfoList.get(count - 1);
                } else if (position == count + 1) {
                    info = adInfoList.get(0);
                } else {
                    int newPosition = position - 1;
                    if (newPosition >= 0 && newPosition < adInfoList.size()) {
                        info = adInfoList.get(position - 1);
                    } else {
                        info = new AdBean();
                        LogUtil.myE("轮播图加载问题，数组越界");
                    }
                }
            } else {
                info = new AdBean();
                LogUtil.myD("轮播图没有数据");
            }

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (position >= 0 && position < imageViews.size()) {
                container.removeView(imageViews.get(position));
            } else {
                LogUtil.myD("轮播图，IndexOutOfBoundsException");
            }
        }

    }


    class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            if (adInfoList != null && adInfoList.size() > 1) {
                switch (arg0) {
                    case 1:
                        isAutoPlay = false;
                        //TODO 当监测到有手势滑动时，暂停后台轮播；重新开始计时。
                        handler.removeCallbacks(task);
                        handler.postDelayed(task, 5000);
                        break;
                    case 2:
                        isAutoPlay = true;
                        break;
                    case 0:
                        if (vp.getCurrentItem() == 0) {
                            vp.setCurrentItem(count, false);
                        } else if (vp.getCurrentItem() == count + 1) {
                            vp.setCurrentItem(1, false);
                        }
                        currentItem = vp.getCurrentItem();
                        isAutoPlay = true;
                        break;
                }
            } else {
//                LogUtil.myD( "一张图片不轮播");
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            if (adInfoList != null && adInfoList.size() > 1) {
                for (int i = 0; i < iv_dots.size(); i++) {
                    if (i == arg0 - 1) {
                        iv_dots.get(i).setImageResource(R.drawable.dot_focus);
                    } else {
                        iv_dots.get(i).setImageResource(R.drawable.dot_blur);
                    }
                }
            } else {
//                LogUtil.myD( "一张图片不轮播2222");
            }
        }
    }
}
