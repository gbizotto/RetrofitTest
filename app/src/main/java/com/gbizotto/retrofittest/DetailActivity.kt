package com.gbizotto.retrofittest

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gbizotto.retrofittest.databinding.ActivityDetailBinding
import com.gbizotto.retrofittest.model.Datum
import com.gbizotto.retrofittest.viewModel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var datum = intent?.getSerializableExtra(getString(R.string.intent_details_datum)) as Datum

        var binding: ActivityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.viewModel = DetailViewModel(datum, this)
    }
}
