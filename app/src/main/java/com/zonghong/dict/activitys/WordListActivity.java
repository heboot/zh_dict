package com.zonghong.dict.activitys;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonghong.dict.R;
import com.zonghong.dict.adapter.CheckWorkAdapter;
import com.zonghong.dict.adapter.WordAdapter;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityWordListBinding;

import java.util.ArrayList;
import java.util.List;

public class WordListActivity extends BaseActivity<ActivityWordListBinding> {

    private WordAdapter wordAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_list;
    }

    @Override
    public void initUI() {
        setBackVisibility(View.VISIBLE);
        binding.includeToolbar.tvTitle.setText("识记");
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
//        wordAdapter = new WordAdapter(datas);
//        binding.rvList.setAdapter(wordAdapter);
    }

    @Override
    public void initListener() {

    }
}
