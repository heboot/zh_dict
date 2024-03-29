package com.zonghong.dict.activitys;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.example.http.HttpClient;
import com.waw.hr.mutils.DialogUtils;
import com.waw.hr.mutils.base.BaseBean;
import com.waw.hr.mutils.bean.CheckResBean;
import com.waw.hr.mutils.bean.CheckWordBean;
import com.waw.hr.mutils.bean.CheckWordLocalBean;
import com.zonghong.dict.R;
import com.zonghong.dict.adapter.CheckWorkAdapter;
import com.zonghong.dict.base.BaseActivity;
import com.zonghong.dict.databinding.ActivityWorkCheckBinding;
import com.zonghong.dict.http.HttpObserver;
import com.zonghong.dict.utils.IntentUtils;
import com.zonghong.dict.utils.SignUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CheckWordActivity extends BaseActivity<ActivityWorkCheckBinding> {

    private CheckWorkAdapter checkWorkAdapter;

    private int currentIndex = 0;

    private List<CheckWordBean> checkWordBeanList;

    private CheckWordBean currentCheckWordBean;

    private int correctNum = 0;

    private int totalSource = 0;

    private Handler handler;

    private int currentWordTime = 0;


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
        loadingDialog = DialogUtils.getLoadingDialog(this, "加载中", false);
        loadingDialog.show();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (currentWordTime <= 0) {
                    currentIndex = currentIndex + 1;
                    checkWorkAdapter.setSelectBean(null);
                    if (currentIndex >= 50) {
                        submit();
                    } else {
                        exeData();
                    }
                } else {
                    currentWordTime = currentWordTime - 1;
                    binding.tvSource.setText(currentWordTime + "");
                    sendEmptyMessageDelayed(11, 1000);
                }
            }
        };
        getData();
    }

    @Override
    public void initListener() {
        binding.tvNext.setOnClickListener((v) -> {
            for (CheckWordLocalBean checkWordLocalBean : checkWorkAdapter.getData()) {

                if ((!checkWordLocalBean.isChecked() && checkWordLocalBean.isCorrect())
                        || (checkWordLocalBean.isChecked() && !checkWordLocalBean.isCorrect())
                        || (!checkWordLocalBean.isChecked() && !checkWordLocalBean.isCorrect())) {
                    //没有答对
                } else {
                    totalSource = totalSource + currentCheckWordBean.getGrade_every();
                    correctNum = correctNum + 1;
                    break;
                }
            }


//            for (CheckWordLocalBean checkWordLocalBean : checkWorkAdapter.getData()) {
//                if (checkWordLocalBean.isChecked()) {
//                    isChoose = true;
//                }
//            }
//
//            if (!isChoose) {
//                tipDialog = DialogUtils.getFailDialog(CheckWordActivity.this, "请先答题后再进行下一题", true);
//                tipDialog.show();
//                return;
//            }
//            isChoose = false;

            currentIndex = currentIndex + 1;

            checkWorkAdapter.setSelectBean(null);

            if (currentIndex >= 50) {
                submit();
            } else {
                exeData();
            }


        });
    }


    private void getData() {
        params = SignUtils.getNormalParams();
        HttpClient.Builder.getServer().test_get(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<List<CheckWordBean>>() {
            @Override
            public void onSuccess(BaseBean<List<CheckWordBean>> baseBean) {
                loadingDialog.dismiss();
                checkWordBeanList = baseBean.getData();
                exeData();
            }

            @Override
            public void onError(BaseBean<List<CheckWordBean>> baseBean) {
                loadingDialog.dismiss();
                tipDialog = DialogUtils.getFailDialog(CheckWordActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }

    private void submit() {
        handler.removeMessages(11);
        params = SignUtils.getNormalParams();
        params.put("grade", totalSource);
        HttpClient.Builder.getServer().grade(params).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new HttpObserver<CheckResBean>() {
            @Override
            public void onSuccess(BaseBean<CheckResBean> baseBean) {

                IntentUtils.intent2CheckResultActivity("您的词汇量检测成绩是" + totalSource + "分\n" + baseBean.getData().getContent());
                totalSource = 0;
                finish();
            }

            @Override
            public void onError(BaseBean<CheckResBean> baseBean) {
                tipDialog = DialogUtils.getFailDialog(CheckWordActivity.this, baseBean.getMsg(), true);
                tipDialog.show();
            }
        });
    }


    private void exeData() {
        handler.removeMessages(11);
        currentCheckWordBean = checkWordBeanList.get(currentIndex);
        binding.tvTitle.setText(currentCheckWordBean.getTitle());
        binding.tvNo.setText(currentIndex + 1 + "");
        currentWordTime = currentCheckWordBean.getElapsed_time();
        binding.tvSource.setText(currentCheckWordBean.getElapsed_time() + "");

        if (checkWorkAdapter == null) {
            checkWorkAdapter = new CheckWorkAdapter(getLocalBean(currentCheckWordBean));
            binding.rvList.setAdapter(checkWorkAdapter);
        } else {
            checkWorkAdapter.getData().clear();
            checkWorkAdapter.getData().addAll(getLocalBean(currentCheckWordBean));
            checkWorkAdapter.notifyDataSetChanged();
        }
        handler.sendEmptyMessageDelayed(11, 1000);
    }

    private String[] options = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N"};

    private List<CheckWordLocalBean> getLocalBean(CheckWordBean checkWordBean) {
        String[] result = checkWordBean.getTest_translate().split(",,");
        List<CheckWordLocalBean> checkWordLocalBeanList = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            CheckWordLocalBean checkWordLocalBean = new CheckWordLocalBean();
            checkWordLocalBean.setOption(options[i]);
            checkWordLocalBean.setTitle(result[i]);
            if (checkWordBean.getResult().indexOf(checkWordLocalBean.getOption()) > -1) {
                checkWordLocalBean.setCorrect(true);
            } else {
                checkWordLocalBean.setCorrect(false);
            }
            checkWordLocalBeanList.add(checkWordLocalBean);
        }

        return checkWordLocalBeanList;
    }


    @Override
    protected void onDestroy() {
        handler.removeMessages(11);
        super.onDestroy();
    }
}
