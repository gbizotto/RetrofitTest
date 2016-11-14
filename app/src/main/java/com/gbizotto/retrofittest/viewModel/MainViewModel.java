package com.gbizotto.retrofittest.viewModel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;


/**
 * Created by gabrielabizotto on 14/11/16.
 */

public class MainViewModel extends BaseObservable {

    public  ObservableField<String> name = new ObservableField<>();

    public MainViewModel() {
      //  this.name = new ObservableField<>();
        //this.name.set("Teste de nome");
    }

//    @Bindable
//    public String getName() {
//        return name.get();
//    }
//
//    public void setName(String name) {
//        this.name.set(name);
//        //notifyPropertyChanged(BR.name);
//    }

    public void onChangeTextClick(){
        Log.v(MainViewModel.class.getSimpleName(),"entrou no onChangeTextClick");
       // setName("Novo nome");
        name.set("nome");
    }
}
