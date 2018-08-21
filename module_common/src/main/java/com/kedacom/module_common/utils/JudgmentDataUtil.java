package com.kedacom.module_common.utils;


import android.text.TextUtils;

import java.util.List;

/**
 * Created by Pan S.Q
 * on 2017/3/6.
 */

public class JudgmentDataUtil {

    /**
     * 判断集合数据是否为空
     *
     * @param list
     * @param <T>  泛型
     * @return true:有 false:无
     */
    public static <T> boolean hasCollectionData(List<T> list) {

        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 获取集合占位值( 适用于轮播图，合作机构等集合在RecyclerView显示为一行时调用 )
     *
     * @param list
     * @param <T>
     * @return 1:占位1行 0：不占位
     */
    public static <T> int getCollectionDataPlaceHolder(List<T> list) {
        if (hasCollectionData(list)) {
            return 1;
        }
        return 0;
    }

    /**
     * 适用于RecyclerView滑动列表单项位置是否为目标项位置
     *
     * @param current 当前位置
     * @param target  目标位置
     * @return true:是 false:否
     */
    public static boolean isCurrentItem(int current, int target) {
        if (current == target) {
            return true;
        }
        return false;
    }

    /**
     * 适用于今日拍品滑动列表当前位置是否是范围项位置
     *
     * @param current 当前位置
     * @param start   起始位置
     * @param end     结束位置
     * @return true:是 false:否
     */
    public static boolean isCurrentRangeItem(int current, int start, int end) {
        if (current > start && current <= end) {
            return true;
        }
        return false;
    }

    /**
     * 适用于明日预展调用{ 明日专场，明日拍品 }
     *
     * @param current 当前位置
     * @param start   起始位置
     * @param end     结束位置
     * @return
     */
    public static boolean isCurrentRangeItemForTomorrow(int current, int start, int end) {
        if (current >= start && current < end) {
            return true;
        }
        return false;
    }

    /**
     * 累计计算多个不同集合类型的总长度
     *
     * @param list 不同集合类型
     * @return
     */
    public static int getAllCollectionTotalCount(List<?>... list) {
        int count = 0;
        int size = list.length;
        for (int i = 0; i < size; i++) {
            if (hasCollectionData(list[i])) {
                count += list[i].size();
            }
        }
        return count;
    }

    /**
     * 根据多个集合数据判断是否显示其他布局视图（例如：今日拍品的专场，推荐，拍品都没数据时显示暂无拍品）
     *
     * @param list 不同的集合
     * @return
     */
    public static int multipleCollectionsShowNone(List<?>... list) {
        if (getAllCollectionTotalCount(list) > 0) {
            return getAllCollectionTotalCount(list);
        }
        return 1;
    }

    /**
     * 单个集合判断显示其他布局
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> int singleCollectionShowNone(List<T> list) {
        if (hasCollectionData(list)) {
            return list.size();
        }
        return 1;
    }

    /**
     * 根据集合确定是否显示标题
     *
     * @param list
     * @param <T>
     * @return
     */
    public static <T> int showTypeTitleView(List<T> list) {
        if (hasCollectionData(list)) {
            return 1;
        }
        return 0;
    }

    /**
     * 是否显示暂无拍品数据
     *
     * @param list
     * @param <T>
     * @return 0：不显示 1：显示
     */
    public static <T> int showNone(List<T> list) {
        if (hasCollectionData(list)) {
            //集合有数据则不显示
            return 0;
        }
        return 1;
    }

    /**
     * 显示占位
     *
     * @param list
     * @param <T>
     * @return 1：占位 0： 不占位
     */
    public static <T> int showPlaceHolder(List<T> list) {
        return showTypeTitleView(list);
    }

    /**
     * 显示占位
     *
     * @param t
     * @param <T>
     * @return 1：占位 0： 不占位
     */
    public static <T> int showPlaceHolder(T t) {
        if (t != null) {
            return 1;
        }
        return 0;
    }

    /**
     * 显示占位
     *
     * @param content
     * @return 1：占位 0： 不占位
     */
    public static int showPlaceHolder(String content) {
        if (!TextUtils.isEmpty(content)) {
            return 1;
        }
        return 0;
    }
}
