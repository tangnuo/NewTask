package com.kedacom.module_main.adapter

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.kedacom.module_common.bean.main.UserInfo
import com.kedacom.module_lib.utils.LogUtil
import com.kedacom.module_main.R

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 13:13
 */
class MainAdapter(private val mContext: Context, sparseArray1: SparseArray<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    private List<String> mList = new ArrayList<>();
    private var sparseArray: SparseArray<String> = sparseArray1
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FunctionViewHolder(mLayoutInflater.inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val routePath = sparseArray[position]
        val fHolder = holder as FunctionViewHolder?
        fHolder!!.tvTitle.text = routePath
        if (position % 3 == 0) {
            fHolder.itemView.setBackgroundColor(Color.parseColor("#fa8072"))
        } else {
            fHolder.itemView.setBackgroundColor(Color.parseColor("#354825"))
        }
        fHolder.itemView.setOnClickListener {
            val userInfo = UserInfo(12, "caowj", "留园")
            if (position == 0) {
                ARouter.getInstance().build(routePath)
                        .withString("name", "曹维健")
                        .withLong("age", 25)
                        .withParcelable("userInfo", userInfo)
                        .navigation()
            } else {
                //简单写法：
//                    ARouter.getInstance().build(routePath).navigation();

                //监听过程：
                ARouter.getInstance().build(routePath).navigation(mContext, object : NavCallback() {
                    override fun onFound(postcard: Postcard) {
                        LogUtil.myD("onFound: 找到了 ")
                    }

                    override fun onLost(postcard: Postcard) {
                        LogUtil.myD("onLost: 找不到了 ")
                    }

                    override fun onArrival(postcard: Postcard) {
                        LogUtil.myD("onArrival: 跳转完了 ")
                    }

                    override fun onInterrupt(postcard: Postcard) {
                        LogUtil.myD("onInterrupt: 被拦截了 ")
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return sparseArray.size()
    }

    internal inner class FunctionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.textView)
    }

}