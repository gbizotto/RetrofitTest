package com.gbizotto.retrofittest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbizotto.retrofittest.databinding.ActivityUsingDataBindingBinding;
import com.gbizotto.retrofittest.viewModel.MainViewModel;

public class UsingDataBindingActivity extends AppCompatActivity {

    MainViewModel mMainViewModel;
    ActivityUsingDataBindingBinding mActivityUsingDataBindingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_using_data_binding);

        mActivityUsingDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_using_data_binding);
        mMainViewModel = new MainViewModel();
        mActivityUsingDataBindingBinding.setViewModel(mMainViewModel);
    }
}
