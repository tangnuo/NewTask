package com.kedacom.module_main.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kedacom.module_common.bean.ItemBean
import com.kedacom.module_main.R

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 13:13
 */
class MainAdapter(private val mContext: Context, list: ArrayList<ItemBean>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var itemList: ArrayList<ItemBean> = list
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FunctionViewHolder(mLayoutInflater.inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bean: ItemBean = itemList[position]
        val fHolder = holder as FunctionViewHolder?
        fHolder!!.tvTitle.text = bean.name
//        if (position % 3 == 0) {
//            fHolder.itemView.setBackgroundColor(Color.parseColor("#fa8072"))
//        } else {
//            fHolder.itemView.setBackgroundColor(Color.parseColor("#354825"))
//        }
        fHolder.itemView.setOnClickListener {
            mContext.startActivity(Intent(mContext, bean.className))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    internal inner class FunctionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.textView)
    }

}