package com.example.caowj.newtask.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * package: com.example.caowj.newtask.widget
 * author: Administrator
 * date: 2017/9/5 15:07
 */
public class MyView1 extends View {
    private Paint linePaint;

    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        linePaint = new Paint();//初始化画笔
        //设置是否抗锯齿;设置抗锯齿会使图像边缘更清晰一些，锯齿痕迹不会那么明显。
        linePaint.setAntiAlias(true);
        //设置填充样式
        //Paint.Style 类型：
        //Paint.Style.FILL_AND_STROKE 填充且描边
        //Paint.Style.STROKE 描边
        //Paint.Style.FILL 填充
        linePaint.setStyle(Paint.Style.STROKE);
        //设置画笔颜色
        linePaint.setColor(Color.GREEN);
        //设置画笔宽度
        linePaint.setStrokeWidth(8);
        //setShadowLayer(float radius, float dx, float dy, int shadowColor) 设置阴影
        //radius ： 表示阴影的倾斜度
        //dx ： 水平位移
        //dy : 垂直位移
        //shadowColor : 阴影颜色
        //这个方法不支持硬件加速，所以我们要测试时必须先关闭硬件加速。
        //加上setLayerType(LAYER_TYPE_SOFTWARE, null); 并且确保你的最小api8以上。
        //linePaint.setShadowLayer(10,15,15,Color.RED);
    }

    //
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //这两个功能一样，都是用来设置背景颜色的。
        canvas.drawColor(Color.parseColor("#F5FFFA"));
        canvas.drawRGB(255, 255, 0);
        //1直线绘制
        //drawLine(float startX, float startY, float stopX, float stopY,@NonNull Paint paint)
        //startX ： 开始点X坐标
        //startY ： 开始点Y坐标
        //stopX ： 结束点X坐标
        //stopY ： 结束点Y坐标
        canvas.drawLine(100, 100, 600, 600, linePaint);

        //2多条直线
        //drawLines(@Size(min=4,multiple=2) float[] pts, int offset, int count, Paint paint)
        //drawLines(@Size(min=4,multiple=2) @NonNull float[] pts, @NonNull Paint paint)
        //pts : 是点的集合且大小最小为4而且是2的倍数。表示每2个点连接形成一条直线，pts 的组织方式为{x1,y1,x2,y2….}
        //offset : 集合中跳过的数值个数，注意不是点的个数！一个点是两个数值
        //count : 参与绘制的数值的个数，指pts[]里数值个数，而不是点的个数，因为一个点是两个数值
        float[] pts = {50, 50, 200, 300, 400, 450, 550, 600};
        //点（50，50）和点（200，200）连接成一条直线；点（400，400）和点（600，600）连接成直线。
        //canvas.drawLines(pts,linePaint);
        //表示从第二个50开始连续的4个点（50，200，200，400）连接的直线
        //canvas.drawLines(pts,1,4,linePaint);

        //3 点即多个点
        //drawPoint(float x, float y, @NonNull Paint paint)
        //canvas.drawPoint(10,10,linePaint);//一个点
        //drawPoints(@Size(multiple=2) @NonNull float[] pts, @NonNull Paint paint)
        //drawPoints(@Size(multiple=2) float[] pts, int offset, int count,@NonNull Paint paint)
        //canvas.drawPoints(pts,linePaint);//多个点

        //4矩形  区别RectF 与Rect ，RectF坐标系是浮点型；Rect坐标系是整形。
        //drawRect(@NonNull RectF rect, @NonNull Paint paint)
        //drawRect(@NonNull Rect r, @NonNull Paint paint)
        //drawRect(float left, float top, float right, float bottom, @NonNull Paint paint)
        //left  指定矩形框左上角的x坐标
        //top： 指定矩形框左上角的y坐标
        //right 指定矩形框右下角的x坐标
        //bottom指定矩形框右下角的y坐标
        //canvas.drawRect(new Rect(20,20,300,200),linePaint);
        //canvas.drawRect(new RectF(20,20,300,200),linePaint);
        //canvas.drawRect(20,20,300,200,linePaint);

        //5圆角矩形
        //RectF： 绘制的矩形
        //rx ： 生成圆角的椭圆X轴半径
        //ry ： 生成圆角的椭圆Y轴的半径
        //drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint)
        //drawRoundRect(float left, float top, float right, float bottom, float rx, float ry,@NonNull Paint paint)
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //这个方法有点坑啊 只能在5.0以上使用,推荐使用下边的方法代替
        //    canvas.drawRoundRect(200,200,1000,800,15,15,linePaint);
        //}
        //canvas.drawRoundRect(new RectF(200,200,1000,800),15,15,linePaint);

        //6圆形
        //drawCircle(float cx, float cy, float radius, @NonNull Paint paint)
        //cx ： 圆心X坐标
        //cy ： 圆心Y坐标
        //radius ： 半径
        //canvas.drawCircle(900,900,500,linePaint);

        //7椭圆 参数为椭圆的矩形边界
        //drawOval(@NonNull RectF oval, @NonNull Paint paint)
        //drawOval(float left, float top, float right, float bottom, @NonNull Paint paint)
        //canvas.drawOval(new RectF(200,200,1000,800),linePaint);
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //    canvas.drawOval(200,200,1000,800,linePaint);
        //}

        //8 圆弧
        //oval ： 生成椭圆的矩形
        //startAngle ： 弧开始的角度 （X轴正方向为0度，顺时针弧度增大）
        //sweepAngle ： 绘制多少弧度 （注意不是结束弧度）
        //useCenter ： 是否有弧的两边 true有两边 false无两边
        //drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter,@NonNull Paint paint)
        //drawArc(float left, float top, float right, float bottom, float startAngle,float sweepAngle, boolean useCenter, @NonNull Paint paint)
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //   canvas.drawArc(100,100,800,600,0,90,true,linePaint);
        //}
        //canvas.drawArc(new RectF(100,100,800,600),0,90,true,linePaint);


    }
}
