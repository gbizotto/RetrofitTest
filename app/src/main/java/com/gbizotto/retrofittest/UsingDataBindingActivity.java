package com.gbizotto.retrofittest;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gbizotto.retrofittest.databinding.ActivityUsingDataBindingBinding;
import com.gbizotto.retrofittest.viewModel.MainViewModel;

public class UsingDataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUsingDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_using_data_binding);
        binding.setViewModel(new MainViewModel());
    }
}
