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
import com.waw.hr.mutils.bean.WordDetailBean;
import com.zonghong.dict.R;
import com.zonghong.dict.databinding.LayoutDetailTipBinding;

public class WordDetailDialog extends DialogFragment {

    private WordDetailBean wordDetailBean;

    private LayoutDetailTipBinding binding;

    public static WordDetailDialog newInstance(WordDetailBean wordDetailBean) {
        Bundle args = new Bundle();
        args.putSerializable(MKey.DATA, wordDetailBean);
        WordDetailDialog fragment = new WordDetailDialog();
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
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_detail_tip, container, false);
        binding = DataBindingUtil.bind(view);
        binding.container.setRadiusAndShadow(
                getResources().getDimensionPixelOffset(R.dimen.x10)
                , getResources().getDimensionPixelOffset(R.dimen.y5), 0.30f);
        wordDetailBean = (WordDetailBean) getArguments().getSerializable(MKey.DATA);
        setCancelable(true);
        binding.llytContainer.setOnClickListener((v) -> {
            dismiss();
        });
        setWordDetailBean(wordDetailBean);
        return view;
    }

    public WordDetailBean getWordDetailBean() {
        return wordDetailBean;
    }

    public void setWordDetailBean(WordDetailBean wordDetailBean) {
        this.wordDetailBean = wordDetailBean;
        binding.tvInfo.setText(wordDetailBean.getTitle() + "\n" + wordDetailBean.getComment().replace(",,", "\n") + "\n" + wordDetailBean.getExample().replace(",,", "\n"));

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

}
