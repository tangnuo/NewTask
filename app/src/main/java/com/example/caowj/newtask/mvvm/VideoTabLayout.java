package com.example.caowj.newtask.mvvm;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.databinding.FragVideoBinding;
import com.example.caowj.newtask.mvvm.adapter.VideoListAdapter;
import com.example.caowj.newtask.mvvm.data.Special;
import com.example.caowj.newtask.mvvm.viewmodel.VideoViewModel;
import com.kedacom.base.BaseListFragment;
import com.kedacom.base.listener.OnItemClickListener;
import com.kedacom.utils.LogUtil;
import com.kedacom.utils.ToastUtil;

/**
 * MVVMDemo
 * <p>
 * MVVM架构： LiveData + Room + RXJava2 + Retrofit + OKHttp + Material Design + Base + Glide构建
 * <br/>
 * 参考：https://github.com/ruzhan123/awaker
 * </p>
 *
 * @Author : caowj
 * @Date : 2018/4/23
 */
public class VideoTabLayout extends BaseListFragment<FragVideoBinding>
        implements OnItemClickListener<Special> {

    private static VideoTabLayout instance = null;

    public static VideoTabLayout getInstance() {
        if (instance == null) {
            instance = new VideoTabLayout();
        }
        return instance;
    }

    private VideoViewModel videoViewModel;

    @Override
    protected int getLayout() {
        return R.layout.frag_video;
    }

    @Override
    protected void initData() {
        videoViewModel = new VideoViewModel();
        binding.setViewModel(videoViewModel);
        setListViewModel(videoViewModel);

        VideoListAdapter adapter = new VideoListAdapter(videoViewModel, this);
        binding.recyclerView.setAdapter(adapter);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LogUtil.myD("监听RecyclerView" + dy);
            }
        });

        videoViewModel.getSpecialLiveData().observe(this, refreshListModel -> {
            if (refreshListModel != null) {
                if (refreshListModel.isRefreshType()) {
                    adapter.setRefreshData(refreshListModel.list);

                } else if (refreshListModel.isUpdateType()) {
                    adapter.setUpdateData(refreshListModel.list);
                }
            }
        });

        videoViewModel.loadSpecialListEntity(String.valueOf(Special.NORMAL));

        onRefresh();
    }

    @Override
    public void onDestroyView() {
        videoViewModel.clear();
        super.onDestroyView();
    }

    @Override
    public void onItemClick(View view, int position, Special bean) {
        ToastUtil.showShortToast(getContext(), "详情页没有实现");
//            SpecialListActivity.launch(getActivity(), bean.id, bean.title, bean.cover);
    }


    public void onDoubleClick() {
        ToastUtil.showShortToast(getContext(), "暂时未处理双击刷新功能");
    }
}
