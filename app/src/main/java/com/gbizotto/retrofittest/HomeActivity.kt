package com.gbizotto.retrofittest

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.util.Log
import butterknife.bindView
import com.gbizotto.retrofittest.adapter.DailyForecastAdapter
import com.gbizotto.retrofittest.api.DarkSkyApi
import com.gbizotto.retrofittest.databinding.ActivityHomeBinding
import com.gbizotto.retrofittest.model.Datum
import com.gbizotto.retrofittest.model.Forecast
import com.gbizotto.retrofittest.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class HomeActivity : AppCompatActivity(), ForecastCallback {

    val BASE_URL = "https://api.darksky.net/"
    val API_KEY = "e890138405ec89f522a93c2749773367"
    val LATITUDE_LONGITUDE = "-30.014735,-51.1951453"

    val mRecyclerView by bindView<RecyclerView>(R.id.recyclerDailyForecast)

    var mBinding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        callWeatherForecst()
    }

    override fun formatPrecipitation(value: Double): String {
        return getString(R.string.precipitation_probability, value.toString())
    }

    override fun formatDate(date: Date): String {
        return DateFormat.getDateFormat(this).format(date)
    }

    private fun initializeRecyclerView(dailies: List<Datum>) {
        val linearLayoutManager: LinearLayoutManager? = LinearLayoutManager(this)
        linearLayoutManager!!.orientation = LinearLayoutManager.VERTICAL

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter: DailyForecastAdapter? = DailyForecastAdapter(this, dailies)
        mRecyclerView.adapter = dailyForecastAdapter
    }

    fun callWeatherForecst() {
        val callback: ForecastCallback = this

        val call: Call<Forecast> = darkSkyApi.getForecast(API_KEY, LATITUDE_LONGITUDE)
        call.enqueue(object : Callback<Forecast> {
            override fun onFailure(call: Call<Forecast>?, t: Throwable?) {
                Log.v(HomeActivity::class.java.simpleName, t?.localizedMessage, t)
            }

            override fun onResponse(call: Call<Forecast>?, response: Response<Forecast>?) {
                val forecast: Forecast? = response?.body()
                val viewModel: HomeViewModel = HomeViewModel(forecast, callback)
                mBinding?.viewModel = viewModel
                initializeRecyclerView(forecast?.daily!!.data)
            }
        })
    }

    val darkSkyApi: DarkSkyApi
        get() {
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(DarkSkyApi::class.java)
        }
}
