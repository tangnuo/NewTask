package com.example.caowj.newtask.example.activity;

import android.arch.paging.DataSource;
import android.arch.paging.ItemKeyedDataSource;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.kedacom.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 学习使用系统框架paging
 * <p>
 * 1、DataSource：获取数据
 * <p>
 * 2、PagedList：数据集合
 * <p>
 * 3、PagedListAdapter：显示数据
 */
public class PagingActivity extends AppCompatActivity {

    private PagedList<DataBean> mPagedList;
    private DataSource mDataSource;

    private RecyclerView mRecyclerView;
    private PagedListAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        // Initialize Data Source
        mDataSource = new MyDataSource();
//        mDataSource = new MyDataSource2();
//        mDataSource = new MyDataSource3();

        makePageList();

        mRecyclerView = findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.submitList(mPagedList);

//        使用PositionalDataSource需要配合监听器，其他两个DataSource可能不需要
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastPos;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                lastPos = mLayoutManager.findLastVisibleItemPosition();

                mPagedList.loadAround(lastPos);//触发Android Paging的加载事务逻辑。
            }
        });
    }

    private void makePageList() {
        // Configure paging
        PagedList.Config mPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(11) //分页数据的数量。在后面的DataSource之loadRange中，count即为每次加载的这个设定值。
                .setPrefetchDistance(15) //初始化时候，预取数据数量。
                .setEnablePlaceholders(false)// Show empty views until data is available
//                .setInitialLoadSizeHint(20)
                .build();

// Build PagedList
        mPagedList = new PagedList.Builder<>(mDataSource, mPagedListConfig)
//                .setConfig(mPagedListConfig)
//                .setDataSource(mDataSource)
                .setNotifyExecutor(new MainThreadTask())
                .setFetchExecutor(new BackgroundThreadTask())
//                .setInitialKey()
//                .setMainThreadExecutor(new BackgroundThreadTask()) //初始化阶段启用
//                .setBackgroundThreadExecutor() //初始化阶段启动
                .build();
    }

    private class BackgroundThreadTask implements Executor {
        public BackgroundThreadTask() {
            this.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("BackgroundThreadTask", "run");
                }
            });
        }

        @Override
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    }

    private class MainThreadTask implements Executor {
        public MainThreadTask() {
            this.execute(new Runnable() {
                @Override
                public void run() {
                    Log.d("MainThreadTask", "run");
                }
            });
        }

        @Override
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    }

    private class MyDataSource extends PositionalDataSource<DataBean> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DataBean> callback) {
            LogUtil.myD("loadInitial1..." + params.pageSize + ",," + params.requestedLoadSize + ",," + params.requestedStartPosition);
            //            List<DataBean> dataBeanList = loadData(0, 20);
            List<DataBean> dataBeanList = loadDataByPosition(params.requestedStartPosition, params.pageSize);
            if (params.placeholdersEnabled) {
                callback.onResult(dataBeanList, params.requestedStartPosition, dataBeanList.size());
            } else {
                callback.onResult(dataBeanList, params.requestedStartPosition);
            }

        }

        @Override
        public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DataBean> callback) {
            LogUtil.myD("loadRange1..." + params.loadSize + ",," + params.startPosition);
//            List<DataBean> dataBeanList = loadData(11, 30);
            List<DataBean> dataBeanList = loadDataByPosition(params.startPosition, params.loadSize);

            callback.onResult(dataBeanList);
        }
    }

    /**
     * 没有合适的接口
     * <p>
     * 参考：https://github.com/SaurabhSandav/PagingDemo
     */
    private class MyDataSource2 extends PageKeyedDataSource<Integer, DataBean> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
            LogUtil.myD("loadInitial2..." + params.requestedLoadSize);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
            LogUtil.myD("loadBefore2..." + params.key + ",," + params.requestedLoadSize);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
            LogUtil.myD("loadAfter2..." + params.key + ",," + params.requestedLoadSize);
        }
    }

    private class MyDataSource3 extends ItemKeyedDataSource<Integer, DataBean> {

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<DataBean> callback) {
            LogUtil.myD("loadInitial3..." + params.requestedInitialKey + ",," + params.requestedLoadSize);
        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {
            LogUtil.myD("loadAfter3..." + params.key + ",," + params.requestedLoadSize);
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {
            LogUtil.myD("loadBefore3..." + params.key + ",," + params.requestedLoadSize);
        }

        @NonNull
        @Override
        public Integer getKey(@NonNull DataBean item) {
            return null;
        }
    }

    /**
     * 通过position请求
     *
     * @param startPosition
     * @param count
     * @return
     */
    private List<DataBean> loadDataByPosition(int startPosition, int count) {
        List<DataBean> list = new ArrayList();

        for (int i = 0; i < count; i++) {
            DataBean data = new DataBean();
            data.id = startPosition + i;
            data.content = "重点在这里" + data.id;
            list.add(data);
        }

        return list;
    }

    private class MyAdapter extends PagedListAdapter<DataBean, MyAdapter.MyViewHolder> {
        public MyAdapter() {
            super(mDiffCallback);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(android.R.layout.simple_list_item_2, null);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            DataBean data = mPagedList.get(position);
            holder.text1.setText(String.valueOf(position));
            holder.text2.setText(String.valueOf(data.content));
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView text1;
            public TextView text2;

            public MyViewHolder(View itemView) {
                super(itemView);

                text1 = itemView.findViewById(android.R.id.text1);
                text1.setTextColor(Color.RED);

                text2 = itemView.findViewById(android.R.id.text2);
                text2.setTextColor(Color.BLUE);
            }
        }
    }

    private DiffUtil.ItemCallback<DataBean> mDiffCallback = new DiffUtil.ItemCallback<DataBean>() {

        @Override
        public boolean areItemsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            Log.d("DiffCallback", "areItemsTheSame");
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull DataBean oldItem, @NonNull DataBean newItem) {
            Log.d("DiffCallback", "areContentsTheSame");
            return TextUtils.equals(oldItem.content, newItem.content);
        }
    };


    /**
     * 实体类
     */
    private class DataBean {
        public int id;
        public String content;
    }


}

