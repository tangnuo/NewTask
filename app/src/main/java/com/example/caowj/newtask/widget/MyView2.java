package com.example.caowj.newtask.widget;

/**
 * Paint画笔  Canvas画布 Path 路径
 * Created by xuenan on 2016/7/14.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Path的方法
 * moveTo    移动起点    移动下一次操作的起点位置
 * lineTo    连接直线    连接上一个点到当前点之间的直线
 * setLastPoint  设置终点    重置最后一个点的位置
 * close     闭合路劲    从最后一个点连接最初的一个点，形成一个闭合区域
 * addRect   添加矩形    添加矩形到当前Path
 * addRoundRect  添加圆角矩形  添加圆角矩形到当前Path
 * addOval   添加椭圆    添加椭圆到当前Path
 * addCircle     添加圆     添加圆到当前Path
 * addPah    添加路劲    添加路劲到当前Path
 * addArc    添加圆弧    添加圆弧到当前Path
 * arcTo     圆弧  绘制圆弧，注意和addArc的区别
 * isEmpty   是否为空    判定Path是否为空
 * isRect    是否为矩形   判定Path是否是一个矩形
 * set   替换路劲    用新的路劲替换当前路劲的所有内容
 * offset    偏移路劲    对当前的路劲进行偏移
 * quadTo    贝塞尔曲线   二次贝塞尔曲线的方法
 * cubicTo   贝塞尔曲线   三次贝塞尔曲线的方法
 * rMoveTo,rlineTo,rQuadTo,rCubicTo  rXxx方法  不带r的方法是基于原点坐标系（偏移量），带r的基于当前点坐标系（偏移量）
 * op    布尔操作    对两个Path进行布尔运算（交集，并集）等操作
 * setFillType   填充模式    设置Path的填充模式
 * getFillType   填充模式    获取Path的填充
 * isInverseFillType     是否逆填充   判断是否是逆填充模式
 * toggleInverseFillType     相反模式    切换相反的填充模式
 * getFillType   填充模式    获取Path的填充
 * incReserve    提示方法    提示Path还有多少个点等待加入
 * computeBounds     计算边界    计算Path的路劲
 * reset，rewind  重置路劲    清除Path中的内容（reset相当于new Path , rewind 会保留Path的数据结构）
 * transform     矩阵操作    矩阵变换
 */
public class MyView2 extends View {
    private Paint path_paint;//路径绘制
    private Paint paint;  //网格绘图
    private Context mcontext;
    private int width2, height2;

    public MyView2(Context context) {
        super(context);
    }

    public MyView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mcontext = context;
        WindowManager manager = (WindowManager) mcontext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        width2 = outMetrics.widthPixels;
        height2 = outMetrics.heightPixels;
        path_paint = new Paint();
        path_paint.setAntiAlias(true);
        path_paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path_paint.setStrokeWidth(10);
        path_paint.setColor(Color.parseColor("#FF0000"));
        //paint.setStyle(Paint.Style.FILL);，设置画笔为实心。一些线条将在画布上看不见。

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

        Path path = new Path();
//        1.lineTo  连接直线    连接上一个点到当前点之间的直线
        path.lineTo(200, 200);
        path.lineTo(400, 0);

        //2.方法名                作用              是否影响之前的操作    是否影响之后的操作
        //moveTo        移动下一次操作的起点位置          否                   是
        //setLastPoint  改变上一次操作点的位置           是                   是
        //当我们绘制线条之前，调用moveTo 和 setLastPoint效果是一样的，
        //都是对坐标原点(0,0)进行操作。setLastPoint是重置上一次操作的最后一点
        //path.lineTo(200, 200);
        //path.moveTo(300,300);
        //path.lineTo(400, 0);

        //path.lineTo(200, 200);
        //path.setLastPoint(300,100);
        //path.lineTo(400, 0);

        //3.close方法连接最后一个点和最初一个点（如果两个点不重合）形成一个闭合的图形。
        //close的作用的封闭路径，如果连接最后一个点和最初一个点任然无法形成闭合的区域，那么close什么也不做。
        //path.moveTo(100,100);
        //path.lineTo(500,100);
        //path.lineTo(300,400);
        //path.close();

        //4.二次贝塞尔曲线
        //public void quadTo(float x1, float y1, float x2, float y2)
        //path.moveTo(100, 400);
        //path.quadTo(300, 100, 400, 400);

        //5.三次贝塞尔曲线
        //public void cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
        //cubicTo方法比quadTo方法多了一个点坐标，那么其中(x1,y1) 为控制点，(x2,y2)为控制点，(x3,y3) 为结束点。
        //path.moveTo(100, 400);
        //path.cubicTo(100, 400, 300, 100, 400, 400);

        //6.1Path中添加基本图形以及区分addArc和arcTo
        //圆形
        //addCircle(float x, float y, float radius, Path.Direction dir)
        //椭圆
        //addOval(RectF oval, Path.Direction dir)
        //addOval(float left, float top, float right, float bottom, Path.Direction dir)
        //矩形
        //addRect(RectF rect, Path.Direction dir)
        //addRect(float left, float top, float right, float bottom, Path.Direction dir)
        //圆角矩形
        //addRoundRect(RectF rect, float rx, float ry, Path.Direction dir)
        //addRoundRect(float left, float top, float right, float bottom, float rx, float ry, Path.Direction dir)
        //addRoundRect(RectF rect, float[] radii, Path.Direction dir)
        //addRoundRect(float left, float top, float right, float bottom, float[] radii, Path.Direction dir)
        //Direction的意思是方向，指导，趋势。点进去跟一下你会发现Direction是一个枚举类型（Enum）
        // 分别有CW（顺时针），CCW（逆时针）两个常量。那么它的作用主要有以下两点：
        //1:在添加图形时确定闭合顺序(各个点的记录顺序)
        //:对自相交图形的渲染结果有影响
        //闭合的问题，相交问题与设置填充模式有关。
        //path.addRect(100, 200, 500, 400, Path.Direction.CCW);
        //path.setLastPoint(400,300);
        //path.addCircle(300,300,200, Path.Direction.CW);

        //6.2addPath路径合并
        //addPath方法就是将两个路径合并到一起。
        //第二个方法的dx,dy指的是偏移量，第三个方法是添加到当前path之前先使用Matrix进行变换
        //public void addPath(Path src)
        //public void addPath(Path src, float dx, float dy)
        //public void addPath(Path src, Matrix matrix)
        //path.addRect(100,100,400,300, Path.Direction.CW);
        //Path src=new Path();
        //src.addCircle(300,300,100, Path.Direction.CW);
        //path.addPath(src,0,100);

        //6.3addArc与arcTo
        //addArc(RectF oval, float startAngle, float sweepAngle)
        //addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle)
        //arcTo(RectF oval, float startAngle, float sweepAngle)
        //arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
        //arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo)
        //区别
        //名称               作用                              区别
        //addArc    添加一个圆弧到Path     直接添加一个圆弧到path中，和上一次操作点无关
        //arcTo     添加一个圆弧到Path     添加一个圆弧到path中，如果圆弧的起点和上次操作点坐标不同就连接两个点
        //startAngle表示开始圆弧度数（0度与X轴方向对齐，顺时针移动，弧度增大）。
        //注意：sweepAngle表示运动了多少弧度，并不是结束弧度。
        //forceMoveTo表示“是否强制使用moveTo”，也就是说是否使用moveTo将上一次操作点移动到圆弧的起点坐标。
        // 默认是false。
        //true  将最后一个点移动到圆弧起点，即不连接最后一个点与圆弧起点
        //false     不移动，而是连接最后一个点与圆弧起点(注意之前没有操作的话，不会连接原点)
        //path.lineTo(200, 200);
        //RectF rectF = new RectF(100, 100, 400, 400);
        //path.arcTo(rectF, 0, 270, true);
        // path.addArc(rectF,0,270);和上面一句等价
        //path.arcTo(rectF, 0, 270, false);

        //6.4 isEmpty、 isRect、 set 和 offset
        //6.4.1 isEmpty判断path中是否包含内容。path.isEmpty()
        //Log.e("-----", "----" + path.isEmpty());//-----: ----true
        //path.lineTo(100,100);
        //Log.e("-----","----"+path.isEmpty());//-----: ----false
        //6.4.2 isRect 判断path是否是一个矩形，如果是一个矩形的话，
        // 会将矩形的信息存放进参数rect中。isRect(RectF rect)
        //RectF rectF = new RectF();
        //rectF.left = 100;
        //rectF.top = 100;
        //rectF.right = 400;
        //rectF.bottom = 300;
        //path.addRect(rectF, Path.Direction.CW);
        //boolean isRect = path.isRect(rectF);
        //Log.e("-----", "------" + isRect);//-----: ------true
        //6.4.3 set public void set(Path src)将新的path赋值到现有path。
        // 相当于运算符中的“=”，如a=b,把b赋值给a
        //path.addRect(100,100,400,300, Path.Direction.CW);
        //Path src=new Path();
        //src.addCircle(300,200,100, Path.Direction.CW);
        //path.set(src);
        //6.4.4 offset平移
        //public void offset(float dx, float dy)
        //public void offset(float dx, float dy, Path dst)
        //这个方法就是对Path进行一段平移，正方向和X轴，Y轴方向一致（如果dx为正数则向右平移，
        //反之向左平移；如果dy为正则向下平移，反之向上平移）。我们看到第二个方法多了一个dst，
        //这个又是一个什么玩意呢，其实参数das是存储平移后的path的。
        //先看两个参数的
        //path.addCircle(300, 200, 100, Path.Direction.CW);
        //path.offset(-100, 100);
        //再看三个参数的
        //从运行效果图可以看出，虽然我们在dst中添加了一个圆形，但是并没有表现出来，
        //所以，当dst中存在内容时，dst中原有的内容会被清空，而存放平移后的path。
        //也就是说dst=path;即dst.set(path)哎 然并卵的东西
        //path.addCircle(300, 200, 100, Path.Direction.CW);
        //Path dst = new Path();
        //dst.addCircle(500, 200, 200, Path.Direction.CW);
        //path.offset(-100, 100, dst);

        //7 FillType 对两个参数取交集 并集 补集
        //public void setFillType(Path.FillType ft)
        //public Path.FillType getFillType()
        //setFillType方法中的参数Path.FillType为枚举类型：
        //FillType.WINDING              取path所有所在区域 默认值
        //FillType.EVEN_ODD             取path所在并不相交区域
        //FillType.INVERSE_WINDING      取path所有未占区域
        //FillType.INVERSE_EVEN_ODD     取path未占或相交区域
        //7.1 WINDING 并集
        //path.addCircle(300,200,100, Path.Direction.CW);
        //path.addCircle(200,200,100, Path.Direction.CW);
        //path.setFillType(Path.FillType.WINDING);
        //7.2 EVEN_ODD
        //path.addCircle(300,200,100, Path.Direction.CW);
        //path.addCircle(200,200,100, Path.Direction.CW);
        //path.setFillType(Path.FillType.EVEN_ODD);
        //7.3 INVERSE_WINDING 补集
        //path.addCircle(300,200,100, Path.Direction.CW);
        //path.addCircle(200,200,100, Path.Direction.CW);
        //path.setFillType(Path.FillType.INVERSE_WINDING);
        //7.4 INVERSE_EVEN_ODD 并集
        path.addCircle(300, 200, 100, Path.Direction.CW);
        path.addCircle(200, 200, 100, Path.Direction.CW);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path.toggleInverseFillType();


        canvas.drawPath(path, path_paint);
    }
}