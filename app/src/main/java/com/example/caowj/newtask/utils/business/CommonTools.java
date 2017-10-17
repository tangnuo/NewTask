package com.example.caowj.newtask.utils.business;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.text.Selection;
import android.text.Spannable;
import android.widget.EditText;

import java.util.List;

/**
 * 和业务相关的工具类
 * package: com.jsfengling.qipai.tools
 * className: CommonTools
 *
 * @author caowj
 *         date：2016年1月21日 上午9:32:41
 */
public class CommonTools {


    /**
     * 根据字符个数计算偏移量
     *
     * @param index 选中tab的下标
     * @param list  标题集合
     * @return 需要移动的长度
     */
    public static int getTabLayoutOffsetWidth(int index, List<String> list) {
        String str = "";
        for (int i = 0; i < index; i++) {
            str += list.get(i);
        }
        return str.length() * 14 + index * 12;
    }

    /**
     * 计算选中的下标移动偏移量
     *
     * @param context
     * @param tab
     * @param index   选中的下标位置
     * @param list    标题集合
     */
    public static void recomputeOffset(Context context, final TabLayout tab, int index, List<String> list) {
        final int width = (int) (getTabLayoutOffsetWidth(index, list) * context.getResources().getDisplayMetrics().density);
        tab.post(new Runnable() {
            @Override
            public void run() {
                tab.smoothScrollTo(width, 0);
            }
        });
    }

    /**
     * 将EditText的光标定位到字符的最后面
     *
     * @param editText 目标文本框
     */
    public static void setEditTextCursorEnd(EditText editText) {
        CharSequence text = editText.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    /**
     * 表示数据大于99时显示99+否则显示实际数量
     *
     * @param count
     * @return
     */
    public static String isMoreThan99(int count) {
        return count > 99 ? "99+" : String.valueOf(count);
    }


    /**
     * EditText竖直方向是否可以滚动 用于订单支付页面中备注滚动产生的事件冲突
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动  false：不可以滚动
     */
    public static boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }
        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }
}
