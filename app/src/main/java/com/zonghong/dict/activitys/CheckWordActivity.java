package com.zonghong.dict.activitys;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonghong.dict.R;
import com.zonghong.dict.adapter.CheckWorkAdapter;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityWorkCheckBinding;
import com.zonghong.dict.utils.IntentUtils;

import java.util.ArrayList;
import java.util.List;

public class CheckWordActivity extends BaseActivity<ActivityWorkCheckBinding> {

    private CheckWorkAdapter checkWorkAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_work_check;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("词汇量检测");
        binding.rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        List<String> datas = new ArrayList<>();
        datas.add("aaa");
        datas.add("aaa");
        datas.add("aaa");
        datas.add("aaa");
        datas.add("aaa");
        checkWorkAdapter = new CheckWorkAdapter(datas);
        binding.rvList.setAdapter(checkWorkAdapter);
    }

    @Override
    public void initListener() {
        binding.tvNext.setOnClickListener((v) -> {
            IntentUtils.doIntent(CheckWorkResultActivity.class);
        });
    }
}
