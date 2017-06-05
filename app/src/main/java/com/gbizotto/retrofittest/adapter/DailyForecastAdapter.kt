package com.gbizotto.retrofittest.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gbizotto.retrofittest.R
import com.gbizotto.retrofittest.databinding.DailyRowBinding
import com.gbizotto.retrofittest.model.Datum
import com.gbizotto.retrofittest.viewModel.DailyForecastViewModel

class DailyForecastAdapter(private val mContext: Context, private val mDailyList: List<Datum>) : RecyclerView.Adapter<DailyForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding = DataBindingUtil.inflate<DailyRowBinding>(layoutInflater, R.layout.daily_row, parent, false)
        return ViewHolder(binding.root, binding, mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val datum = mDailyList[position]
        holder.bindDatum(datum)
    }

    override fun getItemCount(): Int {
        return mDailyList.size
    }

    class ViewHolder(itemView: View, private val mDailyRowBinding: DailyRowBinding, private val mContext: Context) : RecyclerView.ViewHolder(itemView) {
        private var mDailyForecastViewModel: DailyForecastViewModel? = null

        fun bindDatum(datum: Datum) {
            this.mDailyForecastViewModel = DailyForecastViewModel(datum, mContext)
            mDailyRowBinding.dailyViewModel = mDailyForecastViewModel
            mDailyRowBinding.executePendingBindings()
        }
    }
}
