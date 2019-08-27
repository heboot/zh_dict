package com.zonghong.dict.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonghong.dict.R;
import com.zonghong.dict.activitys.RecordWordActivity;
import com.zonghong.dict.adapter.ChooseLevelAdapter;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.ActivityChooseBinding;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShijiChooseFragment extends BaseFragment<ActivityChooseBinding> {


    private ChooseLevelAdapter chooseItemAdapter;


    public static ShijiChooseFragment newInstance() {
        Bundle args = new Bundle();
        ShijiChooseFragment fragment = new ShijiChooseFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.vBack.setVisibility(View.VISIBLE);
        binding.tvTitle.setText("选择单词关卡");
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        List<String> datas = new ArrayList();
        datas.add("!");
        datas.add("!");
        datas.add("!");
        datas.add("!");
        datas.add("!");
        datas.add("!");
        datas.add("!");
        datas.add("!");
        chooseItemAdapter = new ChooseLevelAdapter(datas, new WeakReference((RecordWordActivity) _mActivity));
        binding.rvList.setAdapter(chooseItemAdapter);
    }

    @Override
    public void initListener() {
        binding.includeToolbar.vBack.setOnClickListener((v) -> {
            _mActivity.finish();
        });
    }
}
