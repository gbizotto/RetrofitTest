package com.gbizotto.retrofittest.viewModel

import android.databinding.BaseObservable
import android.databinding.ObservableField

class MainViewModel : BaseObservable() {

    val name = ObservableField<String>()

    fun onChangeTextClick() {
        name.set("nome")
    }
}
