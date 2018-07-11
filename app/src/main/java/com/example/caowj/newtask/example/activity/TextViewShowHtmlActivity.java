package com.example.caowj.newtask.example.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.kedacom.base.mvc.BaseButterKnifeActivity;
import com.kedacom.utils.LogUtil;

import java.net.URL;

import butterknife.BindView;

/**
 * 三种方式实现文本框显示图片
 */
public class TextViewShowHtmlActivity extends BaseButterKnifeActivity {

    @BindView(R.id.textView3)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        showHtmlImage();
//        showLocalImg();
        showProjectImg();
    }


    /**
     * 异步显示图片（先将图片缓存到本地，再次加载本地图片）<p/>
     * 缺点：不允许主线程(UI线程)访问网络
     * <p/>
     * 完整代码参考：http://blog.csdn.net/dreamzml/article/details/9817671
     */
    private void showHtmlImage() {
        Html.ImageGetter imgGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = null;
                URL url;
                try {
                    url = new URL(source);
                    drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
                } catch (Exception e) {
                    return null;
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                        .getIntrinsicHeight());
                return drawable;
            }
        };

        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        String html = "<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
                + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"
                + "</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" +
                "下面是网络图片</p><img src=\"http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg\"/></body></html>";

        tv.setText(Html.fromHtml(html, imgGetter, null));
    }

    /**
     * 加载本地图片
     */
    void showLocalImg() {
        final String sText3 = "测试本地图片信息：<img src=\"/storage/emulated/0/Pictures/Screenshots/11.png\" />";

        final Html.ImageGetter imageGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(String source) {
                LogUtil.myD(mTag + ",,," + source);
                Drawable drawable = null;
                drawable = Drawable.createFromPath(source);
                if (drawable == null) {
                    Toast.makeText(mActivity, "drawable is null", Toast.LENGTH_SHORT).show();
                } else {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                }
                return drawable;
            }

            ;
        };
        tv.setText(Html.fromHtml(sText3, imageGetter, null));
    }


    /**
     * 显示项目中的图片
     */
    private void showProjectImg() {
        String htmlFor02 = "项目中图片测试：" + "<img src='" + R.drawable.home + "'>" + "<img src='"
                + R.drawable.ic_loading_rotate + "'>";

        tv.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
        tv.setText(Html.fromHtml(htmlFor02, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Log.d(mTag, "项目图片测试_source:" + source);
                int id = Integer.parseInt(source);
                Drawable drawable = getResources().getDrawable(id, null);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                return drawable;
            }
        }, null));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_text_view_show_html;
    }

    @Override
    protected void memoryRecovery() {

    }
}
