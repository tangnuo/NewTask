package com.kedacom.module_learn.activity

import android.content.ContentUris
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.caowj.lib_logs.LogUtil
import com.kedacom.module_learn.R
import com.kedacom.module_learn.adapter.AlbumAdapter
import kotlinx.android.synthetic.main.activity_browse_album.*
import kotlin.concurrent.thread

/**
 * 相册预览
 * @property imageList ArrayList<Uri>
 */
class BrowseAlbumActivity : AppCompatActivity() {

    val imageList = ArrayList<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_album)
        recyclerView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                recyclerView.viewTreeObserver.removeOnPreDrawListener(this)
                val columns = 3
                val imageSize = recyclerView.width / columns
                val adapter = AlbumAdapter(this@BrowseAlbumActivity, imageList, imageSize)
                recyclerView.layoutManager = GridLayoutManager(this@BrowseAlbumActivity, columns)
                recyclerView.adapter = adapter
                loadImages(adapter)
                return false
            }
        })
    }

    /**
     * 获取相册中的图片
     * 借助MediaStore API获取到图片的Uri
     * @param adapter AlbumAdapter
     */
    private fun loadImages(adapter: AlbumAdapter) {
        thread {
            val cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, "${MediaStore.MediaColumns.DATE_ADDED} desc")
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID))
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    // content://media/external/images/media/479011
                    Log.d("caowj","读取uri:$uri")
                    imageList.add(uri)
                }
                cursor.close()
            }
            runOnUiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

}
