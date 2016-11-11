package com.gbizotto.retrofittest.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gbizotto.retrofittest.R;
import com.gbizotto.retrofittest.model.Datum;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gabrielabizotto on 11/11/16.
 */

public class DailyForecastAdapter extends ArrayAdapter<Datum> {

    Context mContext;

    public DailyForecastAdapter(Context context, int resource, List<Datum> objects) {
        super(context, resource, objects);
        mContext = context;
    }

    class ViewHolder{
        @BindView(R.id.dailySummary)
        TextView mTxtDailySummary;
        @BindView(R.id.dailyTemperatureMin)
        TextView mTxtDailyTemperatureMin;
        @BindView(R.id.dailyTemperatureMax)
        TextView mDailyTemperatureMax;
        @BindView(R.id.dailyDate)
        TextView mDailyDate;

        ViewHolder(View view){
            ButterKnife.bind(this,view);
            view.setTag(this);
        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_row, parent, false);

            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Datum datum = getItem(position);

        holder.mTxtDailySummary.setText(datum.getSummary());
        holder.mTxtDailyTemperatureMin.setText(mContext.getString(R.string.temperature, Double.toString(datum.getTemperatureMin())));
        holder.mDailyTemperatureMax.setText(mContext.getString(R.string.temperature, Double.toString(datum.getTemperatureMax())));

        holder.mDailyDate.setText(DateFormat.getDateFormat(mContext).format(getDate(datum.getTime())));

        return convertView;
    }

    private Date getDate(long time){
        long dateTime = time * 1000;
        return new Date(dateTime);
    }
}
