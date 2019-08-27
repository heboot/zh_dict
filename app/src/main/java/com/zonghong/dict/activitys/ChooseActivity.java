package com.zonghong.dict.activitys;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonghong.dict.R;
import com.zonghong.dict.adapter.ChooseItemAdapter;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityChooseBinding;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivity extends BaseActivity<ActivityChooseBinding> {

    private ChooseItemAdapter chooseItemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        List<String> datas = new ArrayList();
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");
        chooseItemAdapter = new ChooseItemAdapter(datas);
        binding.rvList.setAdapter(chooseItemAdapter);
    }

    @Override
    public void initListener() {

    }
}
