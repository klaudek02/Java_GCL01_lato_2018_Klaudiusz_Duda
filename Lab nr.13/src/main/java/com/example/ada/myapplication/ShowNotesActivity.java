package com.example.klaudiusz.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ShowNotesActivity extends Activity {
    ListView listView ;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_notes);
        db = new Database(this);
        final List<Note> list = db.getAllNotes();
        listView = (ListView) findViewById(R.id.notesList);
        final List<Note> listV = db.getAllNotes();

        final ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listV);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                List<Integer> selected;
                Bundle bundle = new Bundle();
                Note note = listV.get(position);
                int iid = (int) note.getId();
                note = db.getNote(iid);
                bundle.putString("subject", note.getSubject());
                bundle.putString("description", note.getDescription());
                bundle.putString("photo", note.getPhoto());
                Intent intent = new Intent(getApplicationContext(), ShowNote.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                Note note = listV.get(index);
                int indexR = (int) note.getId();
                note = db.getNote(indexR);
                db.deleteNote(note);
                listV.remove(index);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    }
