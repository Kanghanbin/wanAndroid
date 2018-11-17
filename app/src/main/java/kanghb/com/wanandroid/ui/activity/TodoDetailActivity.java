package kanghb.com.wanandroid.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kanghb.com.wanandroid.R;
import kanghb.com.wanandroid.base.BaseMvpActivity;
import kanghb.com.wanandroid.contract.TodoDetailContract;
import kanghb.com.wanandroid.model.bean.ToDoBean;
import kanghb.com.wanandroid.presenter.TodoDetailPresenter;
import kanghb.com.wanandroid.util.Constant;

public class TodoDetailActivity extends BaseMvpActivity<TodoDetailPresenter> implements TodoDetailContract.View, RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_date)
    TextView etDate;
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
    @BindView(R.id.rb_notdone)
    RadioButton rbNotdone;
    @BindView(R.id.rb_done)
    RadioButton rbDone;
    @BindView(R.id.rg_status)
    RadioGroup rgStatus;
    @BindView(R.id.rv_main)
    NestedScrollView rvMain;
    @BindView(R.id.btn_save)
    Button btnSave;

    private ToDoBean toDoBean;
    private int type = 0;
    private int status = 0;

    @Override
    protected void initToolBar() {
        super.initToolBar();
        getBundle();
        if (toDoBean != null) {
            setToolBar(toolBar, getString(R.string.update));
            btnSave.setText(getString(R.string.update));
            etTitle.setText(toDoBean.getTitle());
            etContent.setText(toDoBean.getContent());
            etDate.setText(toDoBean.getDateStr());
            type = toDoBean.getType();
            status = toDoBean.getStatus();
            if (status == 0) {
                rbDone.setChecked(false);
                rbNotdone.setChecked(true);
            } else {
                rbDone.setChecked(true);
                rbNotdone.setChecked(false);
            }
            switch (type) {
                case Constant.TYPE_ONE:
                    rbStudy.setChecked(false);
                    rbWork.setChecked(false);
                    rbLife.setChecked(false);
                    rbOnly.setChecked(true);
                    break;
                case Constant.TYPE_WORK:
                    rbStudy.setChecked(false);
                    rbWork.setChecked(true);
                    rbLife.setChecked(false);
                    rbOnly.setChecked(false);
                    break;
                case Constant.TYPE_STUDY:
                    rbStudy.setChecked(true);
                    rbWork.setChecked(false);
                    rbLife.setChecked(false);
                    rbOnly.setChecked(false);
                    break;
                case Constant.TYPE_LIFE:
                    rbStudy.setChecked(false);
                    rbWork.setChecked(false);
                    rbLife.setChecked(true);
                    rbOnly.setChecked(false);
                    break;

            }


        } else {
            setToolBar(toolBar, getString(R.string.addtodo));
            btnSave.setText(getString(R.string.addtodo));
        }

    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            toDoBean = (ToDoBean) bundle.getSerializable(Constant.TODOBEAN);
        }
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        rgStatus.setOnCheckedChangeListener(this);
        rgType.setOnCheckedChangeListener(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new TodoDetailPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_todo_detail;
    }

    @Override
    public void showUpdateResult(ToDoBean toDoBean) {
        showToast(getString(R.string.update_success));
        onBackPressedSupport();
    }

    @Override
    public void showAddResult(ToDoBean toDoBean) {
        showToast(getString(R.string.addtodo_success));
        onBackPressedSupport();
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

    }


    @OnClick({R.id.et_date, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_date:
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etDate.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        String desc = String.format("您选择的日期是%d年%d月%d日", year, month + 1, dayOfMonth);
                        showToast(desc);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();

                break;
            case R.id.btn_save:
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();
                String date = etDate.getText().toString().trim();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content) || TextUtils.isEmpty(date)) {
                    showToast(getString(R.string.todo_empty_msg));
                    return;
                }
                if (toDoBean != null) {
                    mPresenter.updateToDo(toDoBean.getId(), title, content, date, type, status);
                } else {
                    mPresenter.addToDo(title, content, date, type);
                }
                break;
        }
    }
}
