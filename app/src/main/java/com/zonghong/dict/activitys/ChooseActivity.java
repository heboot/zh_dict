package com.zonghong.dict.activitys;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.WordTypeBean;
import com.zonghong.dict.MAPP;
import com.zonghong.dict.R;
import com.zonghong.dict.adapter.ChooseItemAdapter;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.common.ChooseType;
import com.zonghong.dict.databinding.ActivityChooseBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.SignUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChooseActivity extends BaseActivity<ActivityChooseBinding> {

    private ChooseItemAdapter chooseItemAdapter;

    private String typeId = "0";

    private ChooseType chooseType;

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
        typeId = getIntent().getExtras().getString(MKey.TYPE_ID);
        chooseType = (ChooseType) getIntent().getExtras().get(MKey.TYPE);
        getData(typeId);
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getData(String t) {
        params = SignUtils.getNormalParams();
        params.put(MKey.TYPE_ID, t);
        HttpClient.Builder.getServer().read(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<WordTypeBean>>() {
            @Override
            public void onSuccess(BaseBean<List<WordTypeBean>> baseBean) {
                if (chooseItemAdapter == null) {
                    chooseItemAdapter = new ChooseItemAdapter(baseBean.getData(), chooseType);
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
                tipDialog = DialogUtils.getFailDialog(ChooseActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }
}
