package com.zonghong.dict.view;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.waw.hr.mutils.MKey;
import com.waw.hr.mutils.bean.WordListBean;
import com.waw.hr.mutils.rxbus.RxBus;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutAddBookTipBinding;
import com.zonghong.dict.utils.BookUtils;

import java.io.Serializable;
import java.util.List;

public class DelBookDialog extends DialogFragment {

    private List<WordListBean> wordListBeanList;

    private LayoutAddBookTipBinding binding;

    public static DelBookDialog newInstance(List<WordListBean> wordListBeanList) {
        Bundle args = new Bundle();
        args.putSerializable(MKey.DATA, (Serializable) wordListBeanList);
        DelBookDialog fragment = new DelBookDialog();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        window.setWindowAnimations(R.style.BottomDialog_Animation);
        //设置边距
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(getResources().getDimensionPixelOffset(R.dimen.x275), getResources().getDimensionPixelOffset(R.dimen.y180));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_book_tip, container, false);
        binding = DataBindingUtil.bind(view);
        binding.tvTitle.setText("是否删除选中的单词？");
        wordListBeanList = (List<WordListBean>) getArguments().getSerializable(MKey.DATA);
        binding.qrbOk.setOnClickListener((v) -> {
            BookUtils.delWordBook(wordListBeanList);
            RxBus.getInstance().post("delWord");
            dismiss();
        });
        binding.qrbNo.setOnClickListener((v) -> {
            dismiss();
        });
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

}
