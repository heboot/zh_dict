package com.zonghong.dict.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.R;
import com.zonghong.dict.adapter.ChooseLevelAdapter;
import com.zonghong.dict.base.BaseFragment;
import com.zonghong.dict.databinding.ActivityChooseBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.SignUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ShijiChooseFragment extends BaseFragment<ActivityChooseBinding> {


    private ChooseLevelAdapter chooseItemAdapter;


    private String typeId;

    public static ShijiChooseFragment newInstance(String typeId) {
        Bundle args = new Bundle();
        args.putString(MKey.TYPE_ID, typeId);
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
        binding.includeToolbar.tvTitle.setText("识记");
        binding.rvList.setLayoutManager(new LinearLayoutManager(_mActivity, RecyclerView.VERTICAL, false));
    }

    @Override
    public void initData() {
        typeId = getArguments().getString(MKey.TYPE_ID);
        getData(typeId);
    }

    @Override
    public void initListener() {
        binding.includeToolbar.vBack.setOnClickListener((v) -> {
            _mActivity.finish();
        });
    }

    private void getData(String t) {
        params = SignUtils.getNormalParams();
        params.put(MKey.TYPE_ID, t);
        HttpClient.Builder.getServer().read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<WordTypeBean>>() {
            @Override
            public void onSuccess(BaseBean<List<WordTypeBean>> baseBean) {
                if (chooseItemAdapter == null) {
                    chooseItemAdapter = new ChooseLevelAdapter(baseBean.getData(), new WeakReference(_mActivity));
                    binding.rvList.setAdapter(chooseItemAdapter);
                } else {
                    chooseItemAdapter.getData().clear();
                    chooseItemAdapter.notifyDataSetChanged();
                    chooseItemAdapter.addData(baseBean.getData());
                    chooseItemAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(BaseBean<List<WordTypeBean>> baseBean) {
                tipDialog = DialogUtils.getFailDialog(_mActivity, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }


}
