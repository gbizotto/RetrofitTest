package com.gbizotto.retrofittest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.gbizotto.retrofittest.adapter.DailyForecastAdapter;
import com.gbizotto.retrofittest.api.DarkSkyApi;
import com.gbizotto.retrofittest.model.Forecast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //public static final String BASE_URL = "https://api.darksky.net/forecast/e890138405ec89f522a93c2749773367/37.8267,-122.4233";
    public static final String BASE_URL = "https://api.darksky.net/";

    private static final String API_KEY = "<YOUR_DARK_SKY_API_KEY>";
    private static final String LATITUDE = "37.8267";
    private static final String LONGITUDE = "-122.4233";
    private static final String LATITUDE_LONGITUDE = "37.8267,-122.4233";


    @BindView(R.id.currentlySummary)
    TextView mCurrentlySummary;
    @BindView(R.id.currentlyPrecipProbability)
    TextView mCurrentlyPrecipProbability;
    @BindView(R.id.lstDailyForecast)
    ListView mLstDailyForecast;

    static DailyForecastAdapter mDailyForecastAdapter;

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mContext = this;

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

                mCurrentlySummary.setText(forecast.getCurrently().getSummary());
                mCurrentlyPrecipProbability.setText(getString(R.string.precipitation_probability, Integer.toString(forecast.getCurrently().getPrecipProbability())));

                mDailyForecastAdapter = new DailyForecastAdapter(mContext, R.layout.daily_row,forecast.getDaily().getData());
                mLstDailyForecast.setAdapter(mDailyForecastAdapter);

            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.v(MainActivity.class.getSimpleName(),"Falhou!");
            }
        });

    }
}
