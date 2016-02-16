package com.example.wyattcooper.practiceapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.firebase.client.Firebase;
import android.widget.TextView;
import com.firebase.client.ValueEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import java.util.Map;
import java.util.HashMap;
import com.example.wyattcooper.practiceapp.entry;
public class Main2Activity extends AppCompatActivity {

    TextView title;
    Button back;
    Button enter;
    EditText datePicker;
    EditText notes;
    entry NewEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Firebase.setAndroidContext(this);

        final Firebase myFirebaseRef = new Firebase("https://dazzling-inferno-9759.firebaseio.com/");

        back = (Button) findViewById(R.id.button2);
        enter = (Button) findViewById(R.id.button3);
        title = (TextView) findViewById(R.id.textView2);
        datePicker = (EditText) findViewById(R.id.editText3);
        notes = (EditText) findViewById(R.id.editText2);

        final Firebase entryRef = myFirebaseRef.child("entries");

        myFirebaseRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();
                if (data != null) {
                    title.setText("Hello " + data);
                    NewEntry = (entry) new entry(data);
                }
            }
            @Override
            public void onCancelled(FirebaseError error) {
            }
        });

        enter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datePicker.getText().toString() != null) {
                    NewEntry.setDate(datePicker.getText().toString());
                    NewEntry.setText(notes.getText().toString());
                    entryRef.push().setValue(NewEntry);
                }
            }
        });

        back.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


