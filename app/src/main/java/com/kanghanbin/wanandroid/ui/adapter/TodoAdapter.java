package com.kanghanbin.wanandroid.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import com.kanghanbin.wanandroid.R;
import com.kanghanbin.wanandroid.model.bean.ToDoBean;
import com.kanghanbin.wanandroid.util.Constant;

/**
 * 创建时间：2018/11/16
 * 编写人：kanghb
 * 功能描述：
 */
public class TodoAdapter extends BaseQuickAdapter<ToDoBean, BaseViewHolder> {
    public TodoAdapter(int layoutResId, @Nullable List<ToDoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ToDoBean item) {

        helper.setText(R.id.tv_todo_title, item.getTitle())
                .setText(R.id.tv_todo_content, item.getContent())
                .setText(R.id.tv_date, !TextUtils.isEmpty(item.getDateStr()) ? item.getDateStr() : "")
                .setText(R.id.tv_completedate, !TextUtils.isEmpty(item.getCompleteDateStr()) ? "完成于 " + item.getCompleteDateStr() : "");
       if(item.getStatus() == 0){
           helper.setText(R.id.tv_todo_transfer,"完成");
       }else {
           helper.setText(R.id.tv_todo_transfer,"复原");
       }
        switch (item.getType()) {
            case Constant.TYPE_ONE:
                helper.setText(R.id.tv_todo_type, R.string.only);
                break;
            case Constant.TYPE_WORK:
                helper.setText(R.id.tv_todo_type, R.string.work);
                break;
            case Constant.TYPE_STUDY:
                helper.setText(R.id.tv_todo_type, R.string.study);
                break;
            case Constant.TYPE_LIFE:
                helper.setText(R.id.tv_todo_type, R.string.life);
                break;

        }
        helper.addOnClickListener(R.id.tv_todo_del);
        helper.addOnClickListener(R.id.tv_todo_transfer);
    }
}
