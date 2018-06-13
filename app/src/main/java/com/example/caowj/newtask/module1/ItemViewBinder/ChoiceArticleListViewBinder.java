package com.example.caowj.newtask.module1.ItemViewBinder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.ArticleLabel;
import com.example.caowj.newtask.example.bean.ChoiceArticle;
import com.example.caowj.newtask.toutiao.util.GlideUtils;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;
import com.example.caowj.newtask.widget.LabelView;
import com.kedacom.utils.AlimmdnUtil;
import com.kedacom.utils.JudgmentDataUtil;
import com.kedacom.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @Author : caowj
 * @Date : 2018/3/16
 */
public class ChoiceArticleListViewBinder extends ItemViewBinder<ChoiceArticle, ChoiceArticleListViewBinder.ArticleViewHolder> {

    private Activity mActivity;

    public ChoiceArticleListViewBinder(Activity mActivity2) {
        this.mActivity = mActivity2;
    }

    @NonNull
    @Override
    protected ArticleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.choice_of_article_item_layout, parent, false);
        return new ArticleViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ArticleViewHolder holder, @NonNull ChoiceArticle choiceArticle) {
        //        精选文章、往期精选

        //文章标题
        holder.tvArticleTitle.setText(choiceArticle.getTitle());
        //文章内容
        holder.tvArticleContent.setText(choiceArticle.getIntroduce());
        int height = MyAndroidUtils.getHeightByScale(mActivity, 0, 0, 0, 3, 2);
        ViewGroup.LayoutParams params = holder.ivArticlePoster.getLayoutParams();
        params.height = height;
        holder.ivArticlePoster.setLayoutParams(params);
        //文章封面
        GlideUtils.loadNormal(mActivity, AlimmdnUtil.modifyImagePath(choiceArticle.getImg()), holder.ivArticlePoster);
//        GlideUtils.loadStringRes(holder.ivArticlePoster, AlimmdnUtil.modifyImagePath(choiceArticle.getImg()));
        //添加标签的控件
        ViewGroup.LayoutParams lp = holder.rl_label.getLayoutParams();
        lp.height = height;
        holder.rl_label.setLayoutParams(lp);
        //初始化清空标签
        holder.rl_label.removeAllViews();
        //添加标签
        List<ArticleLabel> articleLabelList = choiceArticle.getTags();

        if (JudgmentDataUtil.hasCollectionData(articleLabelList)) {

            for (int i = 0; i < articleLabelList.size(); i++) {
                ArticleLabel articleLabel = articleLabelList.get(i);
                LabelView labelView = new LabelView(mActivity);
                //设置方向
                labelView.setDirection(articleLabel.getPosition() == 0 ? LabelView.Direction.RIGHT : LabelView.Direction.LEFT);
                //设置内容
                final String text = articleLabel.getWordposition();
                labelView.setLabelContent(text);
                //设置显示位置
//                LogUtil.d("caowj", "内容：" + text + "\t方向：" + articleLabel.getPosition() + "\t" + (articleLabel.getPosition() == 0 ? "右" : "左") + "\tx:" + articleLabel.getX() + "\ty:" + articleLabel.getY());
                labelView.setDisplayPosition(holder.rl_label, MyAndroidUtils.getScreenWidth(mActivity), height, articleLabel.getX(), articleLabel.getY());
                //标签监听点击
                labelView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyAndroidUtils.showShortToast(mActivity, "暂时未实现");
                    }
                });
            }
        } else {
            LogUtil.d("caowj", "暂无" + choiceArticle.getTitle() + "的标签");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转网页查看详情
                MyAndroidUtils.showShortToast(mActivity, "暂时未实现");
            }
        });

    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_article_poster)
        public
        ImageView ivArticlePoster;//文章封面
        @BindView(R.id.tv_article_title)
        public
        TextView tvArticleTitle;//文章标题
        @BindView(R.id.tv_article_content)
        public
        TextView tvArticleContent;//文章内容
        @BindView(R.id.rl_label)
        public
        RelativeLayout rl_label;//用于添加标签

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
