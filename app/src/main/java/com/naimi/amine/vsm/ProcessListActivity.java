package com.naimi.amine.vsm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.naimi.amine.vsm.Adapters.ProcessListAdapter;
import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.Models.ProcessContents;
import com.naimi.amine.vsm.Network.ProjectApi;
import com.naimi.amine.vsm.Util.DataUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProcessListActivity extends AppCompatActivity {

    ListView processListView;
    List<IndusProcess> processList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process_list);
        processListView = (ListView) findViewById(R.id.process_list_view);
        //  processList = ProcessContents.ITEMS;

        Call<List<IndusProcess>> call = ProjectApi.getInstance().getProcessServ().getProcess();
        call.enqueue(new Callback<List<IndusProcess>>() {
            @Override
            public void onResponse(Call<List<IndusProcess>> call, Response<List<IndusProcess>> response) {
                processList = response.body();
                if (processList != null) {

                    final ProcessListAdapter processAdpt = new ProcessListAdapter(ProcessListActivity.this, processList);

                    processListView.setAdapter(processAdpt);


                    processListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                            DataUtil.setCurrentProcessID(ProcessListActivity.this, processAdpt.getProcessIDbyPosition(position));
                            Toast.makeText(ProcessListActivity.this, "" + processAdpt.getProcessIDbyPosition(position), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ProcessListActivity.this, ProductListActivity.class);
                            startActivity(i);
                        }
                    });

                }

            }

            @Override
            public void onFailure(Call<List<IndusProcess>> call, Throwable t) {

            }
        });


    }
}
