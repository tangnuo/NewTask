package com.kedacom.module_learn.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.kedacom.module_learn.R
import com.kedacom.module_learn.bean.SnapBean

class SanpHelperAdapter(private val context: Context, private val dataList: List<SnapBean>) : RecyclerView.Adapter<SanpHelperAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.img)
        val text: TextView = view.findViewById(R.id.tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.snap_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bean: SnapBean = dataList[position]
        holder.imageView.setImageResource(bean.imgs)
        holder.text.text = bean.text

        //从图片中提取突出颜色
        Palette.from(BitmapFactory.decodeResource(context.resources, bean.imgs)).generate { palette ->
            val vibrantColor = palette!!.getVibrantColor(Color.BLUE)
            holder.text.setTextColor(vibrantColor)
        }
    }
}