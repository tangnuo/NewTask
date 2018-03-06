package com.example.caowj.newtask.widget;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.caowj.newtask.R;

/**
 * DialogFragment代替AlertDialog
 * <p>
 * http://blog.csdn.net/zhq217217/article/details/53333287
 * </p>
 * package: com.example.caowj.newtask.widget
 * author: Administrator
 * date: 2018/3/6 11:28
 */
public class MyDialogFragment1 extends DialogFragment implements View.OnClickListener {
    private static final String NO_TITLE = "no_title";
    private boolean mNoTitle;

    /**
     * @param noTitle 是否含有title
     * @return
     */
    public static MyDialogFragment1 getInstance(boolean noTitle) {
        MyDialogFragment1 customViewDialg = new MyDialogFragment1();
        Bundle bundle = new Bundle();
        bundle.putBoolean(NO_TITLE, noTitle);
        customViewDialg.setArguments(bundle);
        return customViewDialg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNoTitle = getArguments().getBoolean(NO_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mNoTitle) {
            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//设置dialog没有title
        }
        return inflater.inflate(R.layout.item_dialog, container, false);
        //这里需要说明一下，这个view的高度，由布局的所有控件高度与padding和margin的高度和
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        view.findViewById(R.id.tv_cancle).setOnClickListener(this);
        view.findViewById(R.id.tv_ok).setOnClickListener(this);


        //1 通过样式定义,DialogFragment.STYLE_NORMAL这个很关键的
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialog1);

        //2代码设置 无标题 无边框  这个就很坑爹，这么设置很多系统效果就都没有了
        //setStyle(DialogFragment.STYLE_NO_TITLE|DialogFragment.STYLE_NO_FRAME,0);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //注意下面这个方法会将布局的根部局忽略掉，所以需要嵌套一个布局
        Window dialogWindow = getDialog().getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.gravity = Gravity.LEFT | Gravity.TOP;//改变在屏幕中的位置,如果需要改变上下左右具体的位置，比如100dp，则需要对布局设置margin
//            Display defaultDisplay = getActivity().getWindowManager().getDefaultDisplay();
//            lp.width = defaultDisplay.getWidth() - 200;  //改变宽度
//            lp.height=300;//   改变高度
        dialogWindow.setAttributes(lp);

        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {//可以在这拦截返回键啊home键啊事件
                dialog.dismiss();
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancle:
                this.dismiss();
                break;
            case R.id.tv_ok:
                this.dismiss();
                break;
        }
    }
}
