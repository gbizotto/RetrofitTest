package com.gbizotto.retrofittest.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gbizotto.retrofittest.R;
import com.gbizotto.retrofittest.databinding.DailyRowBinding;
import com.gbizotto.retrofittest.model.Datum;
import com.gbizotto.retrofittest.viewModel.DailyForecastViewModel;

import java.util.Date;
import java.util.List;


/**
 * Created by gabrielabizotto on 11/11/16.
 */

public class DailyForecastAdapter extends RecyclerView.Adapter<DailyForecastAdapter.ViewHolder> {

    Context mContext;
    private List<Datum> mDailyList;

    public DailyForecastAdapter(Context context, List<Datum> objects) {
        super();
        mContext = context;
        mDailyList = objects;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        DailyRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.daily_row, parent, false);
        return new ViewHolder(binding.getRoot(),binding);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Datum datum = mDailyList.get(position);
        holder.bindDatum(datum);
    }

    @Override
    public int getItemCount() {
        return mDailyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final DailyRowBinding mDailyRowBinding;
        private DailyForecastViewModel mDailyForecastViewModel;

        public ViewHolder(View itemView, DailyRowBinding mDailyRowBinding) {
            super(itemView);
            this.mDailyRowBinding = mDailyRowBinding;

        }

        public void bindDatum(Datum datum){
            this.mDailyForecastViewModel = new DailyForecastViewModel(datum);
            mDailyRowBinding.setDailyViewModel(mDailyForecastViewModel);
            mDailyRowBinding.executePendingBindings();
        }
    }

    private Date getDate(long time){
        long dateTime = time * 1000;
        return new Date(dateTime);
    }
}
