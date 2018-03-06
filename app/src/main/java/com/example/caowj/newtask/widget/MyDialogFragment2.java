package com.example.caowj.newtask.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.caowj.newtask.R;

/**
 * DialogFragment代替AlertDialog
 * <p>
 * http://blog.csdn.net/zhq217217/article/details/53333287
 * </p>
 * <p>
 * 创建普通AlertDialog
 * package: com.example.caowj.newtask.widget
 * author: Administrator
 * date: 2018/3/6 11:28
 */
public class MyDialogFragment2 extends DialogFragment {
    public static MyDialogFragment2 getInstance() {
        MyDialogFragment2 normalFragmentDialog = new MyDialogFragment2();
        return normalFragmentDialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("普通的DIALOG")
                .setMessage("这是一个普通的dialog")
                .setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        alertDialog.setCancelable(true);
        alertDialog.setCanceledOnTouchOutside(true);
        //和普通的dialog没什么区别了，想干什么就干什么了
        return alertDialog;
    }
}
