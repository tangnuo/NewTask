package com.kedacom.customview.imageView;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @Author : Dick.Pan
 * @Date : 2018/2/26
 * https://blog.csdn.net/little762/article/details/79402970
 * <br/>
 * https://blog.csdn.net/sw5131899/article/details/52838261
 */

public class MultiImageView extends LinearLayout {

    public static int MAX_WIDTH = 0;//控件最大宽度
    private final String TAG = "MultiImageView";
    // 照片的Url列表
    private List<String> imagesList;

    /**
     * 长度 单位为Pixel *
     */
    private int pxOneMaxWandH;  // 单张图最大允许宽高
    private int pxMoreWandH = 0;// 多张图的宽高
    private int pxImagePadding = 13;// 图片间的间距 px

    private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

    private LayoutParams onePicPara;//一张图的布局参数
    private LayoutParams morePara, moreParaColumnFirst;//多张图的布局参数（非第一列、第一列）
    private LayoutParams rowPara;//行布局参数

    private OnItemClickListener mOnItemClickListener;//图片点击监听
    private OnLoadImageListerner onLoadImageListerner;//图片加载监听器

    private Context context;

    public MultiImageView(Context context) {
        super(context);
        this.context = context;
    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setList(List<String> lists, OnLoadImageListerner onLoadImageListerner) throws IllegalArgumentException {
        setList(lists);
        this.onLoadImageListerner = onLoadImageListerner;
    }


    private void setList(List<String> lists) throws IllegalArgumentException {
        if (lists == null) {
            throw new IllegalArgumentException("imageList is null...");
        }
        imagesList = lists;

        if (MAX_WIDTH > 0) {

            // 如果需要两张和四张图横向铺满，
//            if (lists.size() == 2 || lists.size() == 4) {
//                pxMoreWandH = (MAX_WIDTH - pxImagePadding) / 2;
//            } else {
            pxMoreWandH = (MAX_WIDTH - pxImagePadding * 2) / 3; //解决右侧图片和内容对不齐问题
//            }
//            pxOneMaxWandH = MAX_WIDTH * 2 / 3;  // 一张图的时候，图片宽度
            pxOneMaxWandH = MAX_WIDTH * 2 / 3;  // 一张图的时候，图片宽度
            initImageLayoutParams();
        }

        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MAX_WIDTH == 0) {
            int width = measureWidth(widthMeasureSpec);
            if (width > 0) {
                MAX_WIDTH = width - getPaddingLeft() - getPaddingRight();
                if (imagesList != null && imagesList.size() > 0) {
                    setList(imagesList);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 初始化布局参数
     */
    private void initImageLayoutParams() {
        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;

        onePicPara = new LayoutParams(pxOneMaxWandH, wrap);

        moreParaColumnFirst = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara.setMargins(pxImagePadding, 0, 0, 0);

        rowPara = new LayoutParams(match, wrap);
    }

    /**
     * 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
     */
    private void initView() {
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if (MAX_WIDTH == 0) {
            //为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
            addView(new View(getContext()));
            return;
        }

        if (imagesList == null || imagesList.size() == 0) {
            return;
        }

        if (imagesList.size() == 1) {
            //单张图片
            addView(createImageView(0, false));
        } else {
            //多张图片
            int allCount = imagesList.size();
            if (allCount == 4) {
                MAX_PER_ROW_COUNT = 2;
            } else {
                MAX_PER_ROW_COUNT = 3;
            }
            int rowCount = allCount / MAX_PER_ROW_COUNT
                    + (allCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0);// 行数
            for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
                LinearLayout rowLayout = new LinearLayout(getContext());
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                rowLayout.setLayoutParams(rowPara);
                if (rowCursor != 0) {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0);
                }

                int columnCount = allCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT
                        : allCount % MAX_PER_ROW_COUNT;//最后一行的列数
                if (rowCursor != rowCount - 1) {//非最后一行的列数
                    columnCount = MAX_PER_ROW_COUNT;
                }
                addView(rowLayout);

                int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
                for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
                    int position = columnCursor + rowOffset;//图片位置
//                    LogUtil.myD("rowCursor:" + rowCursor + ",,rowOffset:" + rowOffset + ",,columnCursor:" + columnCursor + ",,position:" + position);
                    rowLayout.addView(createImageView(position, true));
                }
            }
        }
    }

    /**
     * 创建ImageView
     *
     * @param position
     * @param isMultiImage
     * @return
     */
    private ImageView createImageView(final int position, final boolean isMultiImage) {
        String url = "";
        if (!TextUtils.isEmpty(imagesList.get(position))) {
            url = imagesList.get(position);
        }

        final ImageView imageView = new ImageView(context);
        if (isMultiImage) {
            //多图
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(position % MAX_PER_ROW_COUNT == 0 ? moreParaColumnFirst : morePara);
            //设置网络图片
            if (onLoadImageListerner != null) {
                onLoadImageListerner.onLoadImage(context, url, imageView);
            } else {
                Log.w("MultiImageView", "MultiImageView 图片加载器出问题了");
            }
//            GlideUtils.loadNormal(context, url, imageView);
//            GlideUtils.loadStringRes(imageView, AlimmdnUtil.modifyImagePath(url), null, null);
        } else {
            //单张图
//            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setScaleType(ImageView.ScaleType.FIT_START);
//            imageView.setMaxHeight(pxOneMaxWandH);
//            imageView.setLayoutParams(onePicPara);
//            imageView.setLayoutParams(new ViewGroup.LayoutParams(pxOneMaxWandH,pxOneMaxWandH));
            if (onLoadImageListerner != null) {
                onLoadImageListerner.onLoadImage(context, url, imageView);
            } else {
                Log.w("MultiImageView", "MultiImageView 图片加载器出问题了");
            }

//            GlideUtils.loadDetailImageBitmap(context, AlimmdnUtil.modifyImagePath(url), new BitmapLoadingListener() {
//                @Override
//                public void onSuccess(Bitmap mBitmap) {
//                    int width = mBitmap.getWidth();
//                    int height = mBitmap.getHeight();
//                    int mHeight = pxOneMaxWandH * height / width;
//                    LinearLayout.LayoutParams mLp = new LinearLayout.LayoutParams(pxOneMaxWandH, mHeight);
//                    imageView.setLayoutParams(mLp);
//                    imageView.setImageBitmap(mBitmap);
//                }
//
//                @Override
//                public void onError() {
//
//                }
//            });
        }
        imageView.setId(url.hashCode());

        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        return imageView;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 自定义加载图片
     */
    public interface OnLoadImageListerner {
        void onLoadImage(Context context, String url, ImageView imageView);
    }
}
