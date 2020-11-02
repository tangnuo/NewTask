package com.kedacom.module_learn.adapter

import android.R
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kedacom.module_common.bean.ItemBean
import java.util.*

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 14:28
 */
class FunctionListAdapter(private val mContext: Context, sparseArray1: ArrayList<ItemBean>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var sparseArray: ArrayList<ItemBean>
    private val mLayoutInflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FunctionViewHolder(mLayoutInflater.inflate(R.layout.simple_list_item_1, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemBean = sparseArray[position]
        val fHolder = holder as FunctionViewHolder?
        fHolder!!.tvTitle.text = itemBean.name
        if (position % 3 == 0) {
            fHolder.itemView.setBackgroundColor(Color.parseColor("#fa8072"))
        } else {
        }
        fHolder.itemView.setOnClickListener {
            val mIntent = Intent(mContext, itemBean.className)
            //                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return sparseArray.size
    }

    fun setSparseArray(sparseArray: ArrayList<ItemBean>) {
        this.sparseArray = sparseArray
    }

    internal inner class FunctionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView

        init {
            tvTitle = view.findViewById(R.id.text1)
        }
    }

    init {
        sparseArray = sparseArray1 ?: ArrayList()
        mLayoutInflater = LayoutInflater.from(mContext)
    }
}