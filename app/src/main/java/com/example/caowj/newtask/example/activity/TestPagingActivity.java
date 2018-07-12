package com.example.caowj.newtask.example.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.ItemKeyedDataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
 * 学习使用系统框架paging，配合LiveData。（无限滚动）
 * <p>
 * 1、DataSource：获取数据
 * <p>
 * 2、PagedList：数据集合
 * <p>
 * 3、PagedListAdapter：显示数据
 * <p>
 * 注意：1.0.0和1.0.1方法有变动。
 */
public class TestPagingActivity extends AppCompatActivity {

    private LiveData<PagedList<DataBean>> mPagedList;
    private DataSource.Factory mFactory;
    private RecyclerView mRecyclerView;
    private PagedListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_layout_recyclerview);
        mFactory = new MyFactory();

        makePageList();

        mRecyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private void makePageList() {
        // Configure paging
        PagedList.Config mPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(11) //分页数据的数量。在后面的DataSource之loadRange中，count即为每次加载的这个设定值。适用于PositionalDataSource
                .setPrefetchDistance(3)   //距底部还有几条数据时，加载下一页数据
                .setEnablePlaceholders(false)//是否启用占位符，若为true，则视为固定数量的item
                .setInitialLoadSizeHint(20)//第一次加载多少数据
                .build();

        mPagedList = new LivePagedListBuilder<>(mFactory, mPagedListConfig)
//                .setNotifyExecutor(new MainThreadExecutor())
                .setFetchExecutor(new MainThreadExecutor())
                .setInitialLoadKey(7)//表示从7开始，适用于ItemKeyedDataSource
                .build();


//        给它设置一个观察，当数据变动时调用adapter.submitList刷新数据，在submitList内部会调用获取下一页的数据的方法。
        mPagedList.observe(this, new Observer<PagedList<DataBean>>() {
            @Override
            public void onChanged(@Nullable PagedList<DataBean> dataBeans) {
                mAdapter.submitList(dataBeans);
            }
        });
    }

    public class MainThreadExecutor implements Executor {

        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mHandler.post(command);
            LogUtil.myD("MainThreadExecutor...");
        }

    }

    private class MyFactory extends DataSource.Factory<Integer, DataBean> {

        @Override
        public DataSource<Integer, DataBean> create() {

//            // 参考：https://www.loongwind.com/archives/367.html
//            return new ItemKeyedDataSource<Integer, DataBean>() {
//                @Override
//                public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<DataBean> callback) {
//                    LogUtil.myD("loadInitial1..." + params.requestedInitialKey + ",," + params.requestedLoadSize);
//                    List<DataBean> dataBeanList = loadDataByKey(params.requestedInitialKey, params.requestedLoadSize);
////
//                    callback.onResult(dataBeanList, params.requestedInitialKey, 100);
//                }
//
//                @Override
//                public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {
////                  //params.key等于item.id
//                    LogUtil.myD("loadAfter1..." + params.key + ",," + params.requestedLoadSize);
//                    List<DataBean> dataBeanList = loadDataByKey(params.key, params.requestedLoadSize);
////
//                    callback.onResult(dataBeanList);
//                }
//
//                @Override
//                public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<DataBean> callback) {
//
//                }
//
//                @NonNull
//                @Override
//                public Integer getKey(@NonNull DataBean item) {
//                    LogUtil.myD("getKey..." + item.id);
//                    return item.id;
//                }
//            };


            // 参考：https://github.com/SaurabhSandav/PagingDemo
            return new PageKeyedDataSource<Integer, DataBean>() {
                @Override
                public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, DataBean> callback) {
                    LogUtil.myD("loadInitial2..." + params.requestedLoadSize);
                    int pageIndex = 1;
                    List<DataBean> dataBeanList = loadDataByIndex(pageIndex, params.requestedLoadSize);

                    callback.onResult(dataBeanList, null, pageIndex + 1);
                }

                @Override
                public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
                    LogUtil.myD("loadBefore2..." + params.requestedLoadSize + ",," + params.key);
                }

                @Override
                public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, DataBean> callback) {
                    LogUtil.myD("loadAfter2..." + params.requestedLoadSize + ",," + params.key);

                    int pageIndex = params.key;

                    List<DataBean> dataBeanList = loadDataByIndex(pageIndex, params.requestedLoadSize);
                    callback.onResult(dataBeanList, pageIndex + 1);
                }
            };


//            return new PositionalDataSource<DataBean>() {
//                @Override
//                public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<DataBean> callback) {
//                    LogUtil.myD("loadInitial3..." + params.pageSize + ",," + params.requestedLoadSize + ",," + params.requestedStartPosition);
//                    List<DataBean> dataBeanList = loadDataByPosition(params.requestedStartPosition, params.requestedLoadSize);
//                    if (params.placeholdersEnabled) {
//                        callback.onResult(dataBeanList, params.requestedStartPosition, dataBeanList.size());
//                    } else {
//                        callback.onResult(dataBeanList, params.requestedStartPosition);
//                    }
//                }
//
//                @Override
//                public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<DataBean> callback) {
//                    LogUtil.myD("loadRange3..." + params.loadSize + ",," + params.startPosition);
//                    List<DataBean> dataBeanList = loadDataByPosition(params.startPosition, params.loadSize);
//
//                    callback.onResult(dataBeanList);
//                }
//            };
        }
    }

    /**
     * 通过position请求
     *
     * @param startPosition 起始位置
     * @param count         数量
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

    /**
     * 分页加载数据
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    private List<DataBean> loadDataByIndex(int pageIndex, int pageSize) {
        List<DataBean> list = new ArrayList();

        for (int i = 0; i < pageSize; i++) {
            DataBean data = new DataBean();
            data.id = pageIndex + i;
            data.content = "这是第 " + pageIndex + " 页的数据：：" + data.id;
            list.add(data);
        }

        return list;
    }

    private List<DataBean> loadDataByKey(int pageIndex, int pageSize) {
        List<DataBean> list = new ArrayList();

        for (int i = 0; i < pageSize; i++) {
            DataBean data = new DataBean();
            data.id = pageIndex + i;
            data.content = "这是第 " + pageIndex + " 页的数据：：" + data.id;
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
            DataBean data = getItem(position);
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

