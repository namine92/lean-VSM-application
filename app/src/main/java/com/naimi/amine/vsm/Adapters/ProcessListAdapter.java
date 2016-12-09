package com.naimi.amine.vsm.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.R;


import java.util.ArrayList;

import java.util.List;

/**
 * Created by root on 26/05/16.
 */
public class ProcessListAdapter extends BaseAdapter {

    Context mContext;

    List<IndusProcess> processList= new ArrayList();;
    List<IndusProcess> processListBackup = new ArrayList();

    public ProcessListAdapter(Context c, List<IndusProcess> ProcessList) {

        mContext = c;
        processListBackup.addAll(ProcessList);
        this.processList.addAll(ProcessList);
    }


    @Override
    public int getCount() {
        return processList.size();
    }

    @Override
    public Object getItem(int position) {
        return processList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_process, container, false);
        }

        final IndusProcess proc = (IndusProcess) getItem(position);


        ((TextView) convertView.findViewById(R.id.process_item_title)).setText(proc.label);

        return convertView;
    }





    public void flitre(String query) {
        processList.clear();
        query = query.toLowerCase();


        notifyDataSetChanged();

    }

    public String getProcessIDbyPosition(int pos){
       return processList.get(pos).reference;

    }


}