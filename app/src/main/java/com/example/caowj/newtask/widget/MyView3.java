package com.example.caowj.newtask.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * 文字绘制
 * Created by xuenan on 2016/7/15.
 */

/**
 * text_paint.setAntiAlias(true); //指定是否使用抗锯齿功能  如果使用会使绘图速度变慢 默认false
 * text_paint.setStyle(Paint.Style.FILL);//绘图样式  对于设文字和几何图形都有效
 * text_paint.setTextAlign(Paint.Align.LEFT);//设置文字对齐方式  取值：align.CENTER、align.LEFT或align.RIGHT 默认align.LEFT
 * text_paint.setTextSize(12);
 * text_paint.setStrokeWidth(5);//设置画笔宽度
 * text_paint.setTextSize(80);//设置文字大小
 * 样式设置
 * text_paint.setFakeBoldText(true);//设置是否为粗体文字
 * text_paint.setUnderlineText(true);//设置下划线
 * text_paint.setTextSkewX((float) -0.25);//设置字体水平倾斜度  普通斜体字是-0.25
 * text_paint.setStrikeThruText(true);//设置带有删除线效果
 * 其它设置
 * text_paint.setTextScaleX(2);//只会将水平方向拉伸  高度不会变
 **/
public class MyView3 extends View {
    private Paint text_paint;
    private Paint paint;  //网格绘图
    private Context mcontext;
    private int width2, height2;

    public MyView3(Context context) {
        super(context);
    }

    public MyView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mcontext = context;
        WindowManager manager = (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width2 = outMetrics.widthPixels;
        height2 = outMetrics.heightPixels;
        text_paint = new Paint();
        text_paint.setStrokeWidth(3);
        text_paint.setTextSize(50);
        text_paint.setColor(Color.RED);

        paint = new Paint();
        paint.setColor(Color.parseColor("#A8A8A8"));
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.parseColor("#F5FFFA"));//画布的背景
        //网格线的绘制
        final int width = width2;
        final int height = height2;
        final int space = 100;   //长宽间隔
        int vertz = 0;
        int hortz = 0;
        for (int i = 0; i < 100; i++) {
            canvas.drawLine(0, vertz, width, vertz, paint);
            canvas.drawLine(hortz, 0, hortz, height, paint);
            vertz += space;
            hortz += space;
        }
        //1、文本绘图样式
        //设置绘图样式 为填充
        text_paint.setStyle(Paint.Style.FILL);
        canvas.drawText("我是一颗小小的石头", 100, 100, text_paint);
//        设置绘图样式 为描边
        text_paint.setStyle(Paint.Style.STROKE);
        canvas.drawText("我是一颗小小的石头", 100, 300, text_paint);
//        设置绘图样式 为填充且描边
        text_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("我是一颗小小的石头", 100, 500, text_paint);

        //2、setTextAlign(Paint.Align align) 文字的对齐方式
        //text_paint.setStyle(Paint.Style.FILL);
        //设置对齐方式  左对齐
        //text_paint.setTextAlign(Paint.Align.LEFT);
        //canvas.drawText("小小的石头", 500,100, text_paint);//点（500,100）在文本的左边
        //设置对齐方式  中间对齐
        //text_paint.setTextAlign(Paint.Align.CENTER);
        //canvas.drawText("小小的石头", 500, 200, text_paint);//点（500,100）在文本的中间
        //设置对齐方式  右对齐
        //text_paint.setTextAlign(Paint.Align.RIGHT);
        //canvas.drawText("小小的石头", 500,300, text_paint);//点（500,100）在文本的右边

        //3、文字样式设置
        //text_paint.setStyle(Paint.Style.FILL);
        //text_paint.setFakeBoldText(true);//是否粗体文字
        //text_paint.setUnderlineText(true);//设置下划线
        //text_paint.setStrikeThruText(true);//设置删除线效果
        //canvas.drawText("小小的石头", 200, 200, text_paint);

        //4、文字倾斜度设置
        //可见普通斜体字是-0.25f，大于-0.25f 向左倾斜，小于 -0.25f 向右倾斜。
        //text_paint.setStyle(Paint.Style.FILL);
        //text_paint.setTextSkewX(-0.25f);
        //canvas.drawText("小小的石头", 100, 100, text_paint);
        //text_paint.setTextSkewX(0.25f);
        //canvas.drawText("小小的石头", 100, 200, text_paint);
        //text_paint.setTextSkewX(-0.5f);
        //canvas.drawText("小小的石头", 100, 300, text_paint);

        //5、水平拉伸设置 仅是水平方向拉伸，高度并未改变。
        //text_paint.setStyle(Paint.Style.FILL);
        //text_paint.setTextScaleX(1);//不拉伸
        //canvas.drawText("小小的石头", 100, 100, text_paint);
        //text_paint.setTextScaleX(2);//水平方向拉伸2倍
        //canvas.drawText("小小的石头", 100, 200, text_paint);
        //text_paint.setTextScaleX(3);//水平方向拉伸3倍
        //canvas.drawText("小小的石头", 100, 300, text_paint);

        //6.canvas绘制文字
        //6.1、drawText
        //drawText(String text, float x, float y, Paint paint)
        //drawText(char[] text, int index, int count, float x, float y, Paint paint)
        //drawText(CharSequence text, int start, int end, float x, float y, Paint paint)
        //下边两个方法要求sdk>=23
        //drawTextRun(char[] text, int index, int count, int contextIndex,
        //int contextCount, float x, float y, boolean isRtl, Paint paint)
        //drawTextRun(CharSequence text, int start, int end, int contextStart,
        //int contextEnd, float x, float y, boolean isRtl, Paint paint)
        //第一个构造函数 ： 是最普通的。
        //第二个构造函数 ： text 字节数组；index 表示第一个要绘制的文字索引；count 需要绘制的文字个数。
        //第三个构造函数 ： text 表示字符 （注意与上面比较）；start 开始截取字符的索引号；
        //end 结束截取字符的索引号。（注意和上面的区别） [start , end ) 包含 start 但不包含 end
        //第四个构造函数和第五个构造函数 ： contextIndex 和 index 相同 ；
        //contextCount 大于等于 count ； isRtl 表示排列顺序，
        //true 表示正序，false 表示倒序（这里的倒是指第一个字符变到最后一个字符，
        //最后一个字符变到第一个字符）。 注意了drawTextRun方法是在 skd23 才引入的方法。
        //text_paint.setStyle(Paint.Style.FILL);
        //canvas.drawText("我是一颗小小的石头".toCharArray(), 1, 4, 100, 100, text_paint);
        //canvas.drawText("我是一颗小小的石头", 1, 4, 100, 200, text_paint);
        //最小sdk23
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //倒序
        //     canvas.drawTextRun("我是一颗小小的石头".toCharArray(), 1, 4, 1, 4, 100, 400, false, text_paint);
        //正序
        //    canvas.drawTextRun("我是一颗小小的石头".toCharArray(), 1, 4, 1, 4, 100, 300, true, text_paint);
        // }

        //6.2、drawPosText
        //drawPosText(String text, float[] pos, Paint paint)
        //drawPosText(char[] text, int index, int count, float[] pos, Paint paint)
        //float[] pos = {100, 100, 200, 200, 300, 300, 400, 400, 500, 500, 600, 600};
        //canvas.drawPosText("我是一颗小小", pos, text_paint);

        //6.3、drawTextOnPath
        //drawTextOnPath(String text, Path path, float hOffset, float vOffset, Paint paint)
        //drawTextOnPath(char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
        //index，count ： 和上面截取参数含义一样，这里不再累诉。
        //hOffset ： 与路径起点的水平偏移量 ，正数向 X 轴正方向移动（右移）；负数向 X 轴负方向移动（左移）
        //如果是圆弧：正数是顺时针的偏移量；反之是逆时针的偏移量
        //vOffset ： 与路径中心的垂直偏移量，正数向 Y 轴正方向移动（下移）；负数向Y 轴负方向移动（上移）
        //如果是圆弧正数向 Y 轴负方向移动（上移）；负数向Y 轴正方向移动（下移）
        //Path mPath = new Path();
        //Paint mpaint = new Paint();
        //mpaint.setStrokeWidth(5);
        //mpaint.setTextSize(80);
        //mpaint.setColor(Color.GREEN);
        //mpaint.setStyle(Paint.Style.STROKE);
        //6.3.1
        //mPath.moveTo(100, 100);
        //mPath.lineTo(800, 100);
        //canvas.drawPath(mPath, mpaint);
        //canvas.drawTextOnPath("我是一颗小小的石头", mPath,10, 100, text_paint);
        //6.3.2
        //mPath.addCircle(500, 500, 200, Path.Direction.CW);
        //canvas.drawPath(mPath, mpaint);
        //canvas.drawTextOnPath("我是一颗小小的石头", mPath, 40,20, text_paint);

        //6.4、Typeface（字体样式设置）
        //setTypeface(Typeface typeface)
        //参数类型是枚举类型，枚举值如下：
        //Typeface.NORMAL //正常体
        //Typeface.BOLD //粗体
        //Typeface.ITALIC //斜体
        //Typeface.BOLD_ITALIC //粗斜体
        //Typeface是用来设置字体样式的，通过paint.setTypeface()来指定。
        //可以指定系统中的字体样式，也可以指定自定义的样式文件中获取。
        //要构建Typeface时，可以指定所用样式的正常体、斜体、粗体等，如果指定样式中，
        //没有相关文字的样式就会用系统默认的样式来显示，一般默认是宋体。
        //Typeface typeface;
        //typeface = Typeface.create("宋体", Typeface.NORMAL);
        //text_paint.setTypeface(typeface);
        //canvas.drawText("我是一颗小小的石头", 100, 100, text_paint);
        //设置楷体根本没起作用，在系统的字体当中没有找到楷体。
        //typeface = Typeface.create("楷体", Typeface.NORMAL);
        //mPaint.setTypeface(typeface);
        //canvas.drawText("我是一颗小小的石头", 100, 200, mPaint);
        //----------------------------------------------------------
        //自定义字体
        //createFromAsset(AssetManager mgr, String path) //Asset中获取
        //createFromFile(File path) //文件路径获取
        //createFromFile(String path) //外部路径获取
        //首先在main下创建assets文件夹，然后在assets文件夹创建fonts文件夹，
        //最后在fonts文件夹下放入font1.ttf
        Typeface typeface;
//        typeface = Typeface.createFromAsset(mcontext.getAssets(), "fonts/font1.ttf");
        //Typeface.createFromFile(mContext.getFilesDir()+"/font1.ttf")
//        text_paint.setTypeface(typeface);
        canvas.drawText("我是一颗小小的石头", 100, 100, text_paint);

//        typeface = Typeface.createFromAsset(mcontext.getAssets(), "fonts/font2.ttf");
//        text_paint.setTypeface(typeface);
        canvas.drawText("我是一颗小小的石头2", 100, 200, text_paint);
    }
}