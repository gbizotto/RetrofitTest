package com.gbizotto.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gbizotto.retrofittest.adapter.DailyForecastAdapter;
import com.gbizotto.retrofittest.api.DarkSkyApi;
import com.gbizotto.retrofittest.databinding.ActivityMainBinding;
import com.gbizotto.retrofittest.model.Datum;
import com.gbizotto.retrofittest.model.Forecast;
import com.gbizotto.retrofittest.viewModel.ForecastViewModel;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AccessActivityInterface{

    //public static final String BASE_URL = "https://api.darksky.net/forecast/e890138405ec89f522a93c2749773367/37.8267,-122.4233";
    public static final String BASE_URL = "https://api.darksky.net/";

    private static final String API_KEY = "<API_KEY>";
    private static final String LATITUDE = "37.8267";
    private static final String LONGITUDE = "-122.4233";
    private static final String LATITUDE_LONGITUDE = "37.8267,-122.4233";

//    static DailyForecastAdapter mDailyForecastAdapter;

    Context mContext;

    ForecastViewModel mForecastViewModel;

    ActivityMainBinding mActivityMainBinding;

    @BindView(R.id.recyclerDailyForecast)
    RecyclerView mRecyclerView;

    DailyForecastAdapter mDailyForecastAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        mContext = this;
        final AccessActivityInterface accessActivityInterface  = this;

        DarkSkyApi darkSkyApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DarkSkyApi.class);

        Call<Forecast> call = darkSkyApi.getForecast(API_KEY, LATITUDE_LONGITUDE);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {

                Log.v(MainActivity.class.getSimpleName(), "request =  "+ call.request().toString());
                Log.v(MainActivity.class.getSimpleName(),"retorno da requisição = " + response.code());
                Forecast forecast = response.body();

                mForecastViewModel = new ForecastViewModel(forecast, accessActivityInterface);
                mActivityMainBinding.setForecastModel(mForecastViewModel);

                initializeRecyclerView(forecast.getDaily().getData());

            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.v(MainActivity.class.getSimpleName(),"Falhou!");
            }
        });

    }

    private void initializeRecyclerView(List<Datum> dailyList){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDailyForecastAdapter = new DailyForecastAdapter(this, dailyList);
        mRecyclerView.setAdapter(mDailyForecastAdapter);
    }

    @Override
    public String formatPrecipitaion(Integer value) {
        return getString(R.string.precipitation_probability, Integer.toString(value));
    }

    @Override
    public String formatDate(Date date) {
        return DateFormat.getDateFormat(this).format(date);
    }

    @OnClick(R.id.next)
    public void onNextClick(){
        startActivity(new Intent(this, UsingDataBindingActivity.class));
    }
}
