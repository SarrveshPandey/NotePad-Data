package com.mobileapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.budgetvm.mobileapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReinstallOS extends Fragment {

    @BindView(R.id.spinnerReinstall)
    Spinner spinnerReinstall;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.reinstall_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

}
