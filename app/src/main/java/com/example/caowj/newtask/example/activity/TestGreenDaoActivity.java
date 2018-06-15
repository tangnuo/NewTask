package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.User;
import com.example.caowj.newtask.example.bean.UserDao;
import com.example.caowj.newtask.example.data.GreenDaoUtils;
import com.kedacom.base.mvc.BaseButterKnifeActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 学习GreenDao使用方法
 * <p>
 * <p/>
 * <p>
 * http://www.jianshu.com/p/00d93c2d511f
 */
public class TestGreenDaoActivity extends BaseButterKnifeActivity {

    @BindView(R.id.etId)
    EditText etId;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnDelete)
    Button btnDelete;
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.tvQuery)
    TextView tvQuery;

    UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDbHelp();
    }

    /*初始化数据库相关*/
    private void initDbHelp() {

        //写法一：
//        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "newTask", null);
//        SQLiteDatabase db = helper.getWritableDatabase();
//        DaoMaster daoMaster = new DaoMaster(db);
//        DaoSession daoSession = daoMaster.newSession();
//        userDao = daoSession.getUserDao();

//        写法二：
        userDao = GreenDaoUtils.getSingleTon(mActivity).getmDaoSession().getUserDao();

//        写法三：
//        userDao = DBManager.getInstance(this).insertUser(new User());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_green_dao;
    }

    @Override
    protected void memoryRecovery() {

    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.btnAdd, R.id.btnDelete, R.id.btnQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                String id = etId.getText().toString();
                String name = etName.getText().toString();
                if (isNotEmpty(id) && isNotEmpty(name)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb
                            .where(UserDao.Properties.Id.eq(id))
                            .list();
                    if (list.size() > 0) {
                        Toast.makeText(mActivity, "主键重复", Toast.LENGTH_SHORT).show();
                    } else {
                        userDao.insert(new User(Long.valueOf(id), name, "name" + id, "sex" + id));
                        Toast.makeText(mActivity, "插入数据成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (isEmpty(id) && isNotEmpty(name)) {
                        Toast.makeText(mActivity, "id为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(name) && isNotEmpty(id)) {
                        Toast.makeText(mActivity, "姓名为空", Toast.LENGTH_SHORT).show();
                    }
                    if (isEmpty(id) && isEmpty(name)) {
                        Toast.makeText(mActivity, "请填写信息", Toast.LENGTH_SHORT).show();
                    }

                }
                etId.setText("");
                etName.setText("");
                break;
            case R.id.btnDelete:
                id = etId.getText().toString();
                if (isNotEmpty(id)) {
                    userDao.deleteByKey(Long.valueOf(id));
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() < 1) {
                        Toast.makeText(mActivity, "删除数据成功", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etName.setText("");
                    }
                } else {
                    Toast.makeText(mActivity, "id为空", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnQuery:
                id = etId.getText().toString();
                if (isNotEmpty(id)) {
                    QueryBuilder qb = userDao.queryBuilder();
                    ArrayList<User> list = (ArrayList<User>) qb.where(UserDao.Properties.Id.eq(id)).list();
                    if (list.size() > 0) {
                        String text = "";
                        for (User user : list) {
                            text = text + "\r\n" + user.getName();
                        }
                        tvQuery.setText(text);
                    } else {
                        tvQuery.setText("");
                        Toast.makeText(mActivity, "不存在该数据", Toast.LENGTH_SHORT).show();
                    }
                    etId.setText("");
                    etName.setText("");
                } else {
                    Toast.makeText(mActivity, "id为空", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private boolean isNotEmpty(String s) {
        if (s != null && !s.equals("") || s.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isEmpty(String s) {
        if (isNotEmpty(s)) {
            return false;
        } else {
            return true;
        }
    }

}
