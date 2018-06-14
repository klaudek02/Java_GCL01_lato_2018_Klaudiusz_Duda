package com.example.klaudiusz.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowNote extends Activity {
    TextView subjectText;
    TextView descriptionText;
    ImageView iv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        subjectText = (TextView)findViewById(R.id.subjectText);
        descriptionText = (TextView) findViewById(R.id.descriptionText);
        iv = (ImageView) findViewById(R.id.photoView);
        Bundle bundle = getIntent().getExtras();
        String subject = bundle.getString("subject");
        String description =bundle.getString("description");
        String photo = bundle.getString("photo");
        subjectText.setText(subject);
        descriptionText.setText(description);
        byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        iv.setImageBitmap(decodedByte);
    }
}
