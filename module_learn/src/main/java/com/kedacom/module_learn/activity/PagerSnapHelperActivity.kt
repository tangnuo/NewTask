package com.kedacom.module_learn.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.kedacom.module_learn.R
import com.kedacom.module_learn.adapter.SanpHelperAdapter
import com.kedacom.module_learn.bean.SnapBean
import kotlinx.android.synthetic.main.activity_pager_snap_helper.*
import java.util.*

/**
 *  RecyclerView + SnapHelper实现炫酷ViewPager效果
 *
 *  https://www.jianshu.com/p/f23f6271a074
 *
 *  作者：Caowj
 *  邮箱：caoweijian@kedacom.com
 *  日期：2020/12/18 10:36
 */
class PagerSnapHelperActivity : AppCompatActivity() {
    private lateinit var mDatas: ArrayList<SnapBean>
    private val imgs = intArrayOf(R.mipmap.a1, R.mipmap.a2, R.mipmap.a3, R.mipmap.a4, R.mipmap.a5, R.mipmap.a6)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager_snap_helper)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView.layoutManager = linearLayoutManager

        mDatas = ArrayList<SnapBean>()
        for (x in imgs.indices) {
            val testBean = SnapBean()
            testBean.text = ((x + 1).toString() + "->我已经厌倦了嫌恶别人、憎恨别人的生活，厌倦了无法爱任何人的生活。我连一个朋友也没有，哪怕是一个。最重要的是，我甚至连自己都爱不起来。")
            testBean.imgs = (imgs[x])
            mDatas.add(testBean)
        }
        recyclerView.adapter = SanpHelperAdapter(this, mDatas)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)


    }
}