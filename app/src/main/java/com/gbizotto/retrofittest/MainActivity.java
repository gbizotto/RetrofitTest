package com.gbizotto.retrofittest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity implements ForecastCallback {

    private static final String BASE_URL = "https://api.darksky.net/";

    private static final String API_KEY = "";
    private static final String LATITUDE_LONGITUDE = "-30.014735,-51.1951453";

    @BindView(R.id.recyclerDailyForecast)
    RecyclerView mRecyclerView;

    private ForecastViewModel mForecastViewModel;
    private ActivityMainBinding mActivityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ButterKnife.bind(this);

        final ForecastCallback callback = this;

        DarkSkyApi darkSkyApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DarkSkyApi.class);

        Call<Forecast> call = darkSkyApi.getForecast(API_KEY, LATITUDE_LONGITUDE);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                mForecastViewModel = new ForecastViewModel(forecast, callback);
                mActivityMainBinding.setForecastModel(mForecastViewModel);
                initializeRecyclerView(forecast.getDaily().getData());
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.v(MainActivity.class.getSimpleName(), t.getLocalizedMessage(), t);
            }
        });
    }

    private void initializeRecyclerView(List<Datum> dailyList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DailyForecastAdapter mDailyForecastAdapter = new DailyForecastAdapter(this, dailyList);
        mRecyclerView.setAdapter(mDailyForecastAdapter);
    }

    @OnClick(R.id.next)
    public void onNextClick() {
        startActivity(new Intent(this, UsingDataBindingActivity.class));
    }

    @Override
    public String formatPrecipitation(double value) {
        return getString(R.string.precipitation_probability, Double.toString(value));
    }

    @Override
    public String formatDate(Date date) {
        return DateFormat.getDateFormat(this).format(date);
    }
}
