package com.example.caowj.newtask.adapter

import android.content.Context
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import com.example.caowj.newtask.R
import com.kedacom.module_common.bean.main.UserInfo

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 13:13
 */
class IndexAdapter(private val mContext: Context, array: SparseArray<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    private var sparseArray: SparseArray<String> = array
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        return FunctionViewHolder(mLayoutInflater.inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        val routePath = sparseArray[position]
        val fHolder = holder as FunctionViewHolder?
        fHolder!!.tvTitle.text = routePath
//        if (position % 3 == 0) {
//            fHolder.itemView.setBackgroundColor(Color.parseColor("#fa8072"))
//        } else {
//            fHolder.itemView.setBackgroundColor(Color.parseColor("#354825"))
//        }
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
                        Log.d("caowj", "onFound: 找到了 ")
                    }

                    override fun onLost(postcard: Postcard) {
                        Log.d("caowj", "onLost: 找不到了 ")
                    }

                    override fun onArrival(postcard: Postcard) {
                        Log.d("caowj", "onArrival: 跳转完了 ")
                    }

                    override fun onInterrupt(postcard: Postcard) {
                        Log.d("caowj", "onInterrupt: 被拦截了 ")
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return sparseArray.size()
    }

    internal inner class FunctionViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.textView)
    }

}