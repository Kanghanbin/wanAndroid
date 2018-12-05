package com.kanghanbin.wanandroid.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kanghanbin.wanandroid.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.kanghanbin.wanandroid.base.BaseMvpActivity;
import com.kanghanbin.wanandroid.contract.TodoContract;
import com.kanghanbin.wanandroid.model.bean.DoneListBean;
import com.kanghanbin.wanandroid.model.bean.ToDoBean;
import com.kanghanbin.wanandroid.presenter.TodoPresenter;
import com.kanghanbin.wanandroid.ui.adapter.TodoAdapter;
import com.kanghanbin.wanandroid.util.IntentUtil;

public class TodoActivity extends BaseMvpActivity<TodoPresenter> implements TodoContract.View,
        RadioGroup.OnCheckedChangeListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.rb_notdone)
    RadioButton rbNotdone;
    @BindView(R.id.rb_done)
    RadioButton rbDone;
    @BindView(R.id.rg_status)
    RadioGroup rgStatus;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rb_only)
    RadioButton rbOnly;
    @BindView(R.id.rb_work)
    RadioButton rbWork;
    @BindView(R.id.rb_study)
    RadioButton rbStudy;
    @BindView(R.id.rb_life)
    RadioButton rbLife;
    @BindView(R.id.rg_type)
    RadioGroup rgType;

    private MenuItem menuAdd;
    private TodoAdapter adapter;
    private List<ToDoBean> toDoBeanList;
    private int type = 0;
    private int status = 0;
    private int pageDone = 1;
    private int pageTodo = 1;

    @Override
    protected void initPresenter() {
        mPresenter = new TodoPresenter();
        toDoBeanList = new ArrayList<>();
        adapter = new TodoAdapter(R.layout.item_todo, toDoBeanList);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        rvMain.setLayoutManager(new LinearLayoutManager(mContext));
        rvMain.setAdapter(adapter);
        setAuto();

    }

    private void setAuto() {
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                if (status == 0) {
                    pageTodo = 1;
                    mPresenter.getListNotDoneList(type, pageTodo);
                } else {
                    pageDone = 1;
                    mPresenter.getListDoneList(type, pageDone);
                }
                smartRefreshLayout.finishRefresh(800);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if (status == 0) {
                    pageTodo++;
                    mPresenter.getMoreListNotDoneList(type, pageTodo);
                } else {
                    pageDone++;
                    mPresenter.getMoreListDoneList(type, pageDone);
                }
                smartRefreshLayout.finishLoadMore(800);
            }
        });
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        rgType.setOnCheckedChangeListener(this);
        rgStatus.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        setToolBar(toolBar, getString(R.string.todo));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_todo;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        menuAdd = menu.findItem(R.id.item_todo);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_todo:
                IntentUtil.startTodoDetailActiviy(mContext, null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showNotDoneListBean(DoneListBean notDoListBean) {
        toDoBeanList = notDoListBean.getDatas();
        adapter.replaceData(toDoBeanList);
    }

    @Override
    public void showMoreNotDoneListBean(DoneListBean notDoListBean) {
        if (notDoListBean.getDatas().size() == 0) {
            pageTodo--;
        } else {
            toDoBeanList.addAll(notDoListBean.getDatas());
            adapter.addData(notDoListBean.getDatas());
        }
    }

    @Override
    public void showListDoneBean(DoneListBean doneListBean) {
        toDoBeanList = doneListBean.getDatas();
        adapter.replaceData(toDoBeanList);

    }

    @Override
    public void showMoreListDoneBean(DoneListBean doneListBean) {
        if (doneListBean.getDatas().size() == 0) {
            pageTodo--;
        } else {
            toDoBeanList.addAll(doneListBean.getDatas());
            adapter.addData(doneListBean.getDatas());
        }
    }

    @Override
    public void showDeleteToDoBean(int pos) {
        if (adapter.getData().size() <= pos || adapter.getData().size() < 0) {
            return;
        }
        toDoBeanList.remove(pos);
        adapter.remove(pos);

    }

    @Override
    public void showDoneResult(int pos, ToDoBean toDoBean) {
        if (adapter.getData().size() <= pos || adapter.getData().size() < 0) {
            return;
        }
        toDoBeanList.remove(pos);
        adapter.remove(pos);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_done:
                status = 1;
                break;
            case R.id.rb_notdone:
                status = 0;
                break;
            case R.id.rb_only:
                type = 0;

                break;
            case R.id.rb_work:
                type = 1;
                break;
            case R.id.rb_study:
                type = 2;
                break;
            case R.id.rb_life:
                type = 3;
                break;

        }
        if (status == 0) {
            pageTodo = 1;
            mPresenter.getListNotDoneList(type, pageTodo);
        } else {
            pageDone = 1;
            mPresenter.getListDoneList(type, pageDone);
        }

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getData().size() <= position || adapter.getData().size() < 0) {
            return;
        }
        ToDoBean toDoBean = (ToDoBean) adapter.getData().get(position);
        switch (view.getId()) {
            case R.id.tv_todo_del:
                mPresenter.deleteToDoBean(toDoBean.getId(), position);
                break;
            case R.id.tv_todo_transfer:
                int st = toDoBean.getStatus() == 0 ? 1 : 0;
                mPresenter.doneToDo(toDoBean.getId(), st, position);
                break;
        }


    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (adapter.getData().size() <= position || adapter.getData().size() < 0) {
            return;
        }
        ToDoBean toDoBean = (ToDoBean) adapter.getData().get(position);
        IntentUtil.startTodoDetailActiviy(mContext, toDoBean);
    }
}
