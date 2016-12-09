package com.naimi.amine.vsm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naimi.amine.vsm.Models.Pojo.Timing;
import com.naimi.amine.vsm.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 26/05/16.
 */
public class TimingListAdapter extends BaseAdapter {

    Context mContext;

    List<Timing> timingList= new ArrayList();
    List<Timing> TimingListBackup = new ArrayList();

    public TimingListAdapter(Context c, List<Timing> timingList) {

        mContext = c;
        TimingListBackup.addAll(timingList);
        this.timingList.addAll(timingList);
    }


    @Override
    public int getCount() {
        return timingList.size();
    }

    @Override
    public Object getItem(int position) {
        return timingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_timing, container, false);
        }

        final Timing time = (Timing) getItem(position);

        ((TextView) convertView.findViewById(R.id.PDC_title)).setText(time.productId);
        ((TextView) convertView.findViewById(R.id.timing_info)).setText("" + time.time);


        return convertView;
    }


    public void flitre(String query) {
        timingList.clear();
        query = query.toLowerCase();


        notifyDataSetChanged();

    }

    public void update(List<Timing> timingList) {

        TimingListBackup.clear();
        TimingListBackup.addAll(timingList);


        this.timingList.clear();
        this.timingList.addAll(timingList);

        notifyDataSetChanged();

    }

}