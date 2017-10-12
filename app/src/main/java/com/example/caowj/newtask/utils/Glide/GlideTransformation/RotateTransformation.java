package com.example.caowj.newtask.utils.Glide.GlideTransformation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 图片旋转
 * package: com.jsfengling.qipai.tools.Glide.MyTransformation
 * author: caowj
 * date: 2016/9/23 17:31
 */
public class RotateTransformation extends BitmapTransformation {

    private float rotateRotationAngle = 0f;

    /**
     * 图片旋转
     *
     * @param context             上下文
     * @param rotateRotationAngle 旋转的角度
     */
    public RotateTransformation(Context context, float rotateRotationAngle) {
        super(context);

        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public String getId() {
        return "rotate" + rotateRotationAngle;
    }
}
