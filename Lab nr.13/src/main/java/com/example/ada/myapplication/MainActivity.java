package com.example.klaudiusz.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnNewNote;
    private Button btnShowAll;
    private TextView textInternet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewNote = (Button) findViewById(R.id.btnNewNote);
        btnNewNote.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startAnotherActivity = new Intent(getApplicationContext(), NewNoteActivity.class);
                startActivity(startAnotherActivity);
            }
        });
        btnShowAll = (Button) findViewById(R.id.btnAllNotes);
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent startAnotherActivity = new Intent(getApplicationContext(), ShowNotesActivity.class);
                startActivity(startAnotherActivity);
            }
        });
    }

    ;
}
