package com.naimi.amine.vsm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.naimi.amine.vsm.Adapters.TimingListAdapter;
import com.naimi.amine.vsm.Models.PDCContents;
import com.naimi.amine.vsm.Models.Pojo.IndusProcess;
import com.naimi.amine.vsm.Models.Pojo.PDC;
import com.naimi.amine.vsm.Models.Pojo.Timing;
import com.naimi.amine.vsm.Models.TimingContents;
import com.naimi.amine.vsm.Network.ProjectApi;
import com.naimi.amine.vsm.Util.DataUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimingsListActivity extends AppCompatActivity {

    TimingListAdapter tmAdpt;
    ListView timingList;
    AlertDialog addTimingDialogue;
    FloatingActionButton addTimingBtn;
    Spinner pdcSpinner;
    Spinner perteSpinner;
    long timeWhenStopped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timing_list);

        timingList = (ListView) findViewById(R.id.timing_list);
        addTimingBtn = (FloatingActionButton) findViewById(R.id.add_timing);
        tmAdpt = new TimingListAdapter(this, TimingContents.ITEMS);

        addTimingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTimeStamp();
            }
        });
        timingList.setAdapter(tmAdpt);

    }

    void addTimeStamp() {


        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialogue_add_timing, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        timeWhenStopped = 0;

        builder.setCancelable(true);

        builder.setTitle("Ajouter un chronométrage");
        //builder.setMessage(article.getText());
        builder.setInverseBackgroundForced(true);


        builder.setNegativeButton("Retour", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });


        builder.setPositiveButton("Enregistrer", null);


        addTimingDialogue = builder.create();

        addTimingDialogue.setView(layout);


        final Chronometer chrono = (Chronometer) layout.findViewById(R.id.chrono);
        ImageButton startChronoBtn = (ImageButton) layout.findViewById(R.id.btn_start_chrono);
        ImageButton stopChronoBtn = (ImageButton) layout.findViewById(R.id.btn_stop_chrono);
        ImageButton replayChronoBtn = (ImageButton) layout.findViewById(R.id.btn_replay_chrono);


        addTimingDialogue.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(final DialogInterface dialog) {

                Button b = addTimingDialogue.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // add the timing
                        String perte = "NA";

                        long time = Math.abs(timeWhenStopped);


                        if (time == 0)
                            Toast.makeText(TimingsListActivity.this, "Le chronometrage ne doit pas etre null", Toast.LENGTH_SHORT).show();
                        else {
                            if (pdcSpinner.getSelectedItem().toString().equals("Machine"))
                                Toast.makeText(TimingsListActivity.this, "Vous devez selectionner une machine", Toast.LENGTH_SHORT).show();

                            else {
                                if (!pdcSpinner.getSelectedItem().toString().equals("Perte"))
                                    perte = pdcSpinner.getSelectedItem().toString();


                                TimingContents.addItem(new Timing("", time, perte, "id", pdcSpinner.getSelectedItem().toString(), ""));

                                tmAdpt.update(TimingContents.ITEMS);


                                Toast.makeText(TimingsListActivity.this, "" + time, Toast.LENGTH_SHORT).show();

                                timeWhenStopped = 0;
                                dialog.dismiss();
                            }
                        }

                    }
                });
            }
        });


        pdcSpinner = (Spinner) layout.findViewById(R.id.pdc_spin);

        perteSpinner = (Spinner) layout.findViewById(R.id.perte_spin);

        //initilizePDCSpinner(PDCContents.ITEMS);

        initilizePerteSpinner();

        Call<List<PDC>> call = ProjectApi.getInstance().getProcessServ().getProcessPDCs(
                DataUtil.getCurrentProcessID(TimingsListActivity.this));
        call.enqueue(new Callback<List<PDC>>() {
            @Override
            public void onResponse(Call<List<PDC>> call, Response<List<PDC>> response) {

                if (response != null)
                    initilizePDCSpinner(response.body());
            }

            @Override
            public void onFailure(Call<List<PDC>> call, Throwable t) {

            }
        });


        startChronoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chrono.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chrono.start();
            }
        });

        stopChronoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                timeWhenStopped = chrono.getBase() - SystemClock.elapsedRealtime();
                chrono.stop();
            }
        });

        replayChronoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chrono.stop();
                chrono.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;


            }
        });
        addTimingDialogue.show();

    }


    private void initilizePDCSpinner(List<PDC> pdcList) {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (PDC pdc : pdcList)
            adapter.add(pdc.label);


        adapter.add("Machine");

        pdcSpinner.setAdapter(adapter);
        pdcSpinner.setSelection(adapter.getCount()); //display hint


        // You can create an anonymous listener to handle the event when is selected an spinner item
    }


    private void initilizePerteSpinner() {


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adapter.add("NA");
        adapter.add("VA");

        adapter.add("Perte");

        perteSpinner.setAdapter(adapter);
        perteSpinner.setSelection(adapter.getCount()); //display hint


        // You can create an anonymous listener to handle the event when is selected an spinner item
    }


}
