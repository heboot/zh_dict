package com.zonghong.dict.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zonghong.dict.R;
import com.zonghong.dict.adapter.WordAdapter;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.ActivityChooseBinding;
import com.zonghong.dict.databinding.ActivityWordListBinding;
import com.zonghong.dict.view.AddBookDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShijiWordListFragment extends BaseFragment<ActivityWordListBinding> {

    private WordAdapter wordAdapter;

    private AddBookDialog addBookDialog;

    public static ShijiWordListFragment newInstance() {
        Bundle args = new Bundle();
        ShijiWordListFragment fragment = new ShijiWordListFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_word_list;
    }

    @Override
    public void initUI() {
        binding.includeToolbar.tvTitle.setText("识记");
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        List<String> datas = new ArrayList<>();
        datas.add("aaa");
        datas.add("aaa");
        datas.add("aaa");
        datas.add("aaa");
        datas.add("aaa");
        wordAdapter = new WordAdapter(datas, new WeakReference<>(ShijiWordListFragment.this));
        binding.rvList.setAdapter(wordAdapter);
    }

    @Override
    public void initListener() {

    }

    public void showDetailView() {
        binding.detailTip.show();
    }

    public void showAddView() {
        if (addBookDialog == null) {
            addBookDialog = AddBookDialog.newInstance();
        }
        addBookDialog.show(getFragmentManager(), "");

    }
}
