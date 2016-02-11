package com.example.wyattcooper.practiceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.NumberPicker;
import java.util.Random;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.AuthData;
public class MainActivity extends AppCompatActivity {

    TextView textView;
    NumberPicker np;
    EditText editable;
    Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        final Firebase myFirebaseRef = new Firebase("https://dazzling-inferno-9759.firebaseio.com/");

        enter = (Button) findViewById(R.id.button);

        editable = (EditText) findViewById(R.id.editText);

        textView = (TextView) findViewById(R.id.textView);

        enter.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                myFirebaseRef.child("name").setValue(editable.getText().toString());
            }
        });

        myFirebaseRef.child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();
                if (data != null){
                    textView.setText("Hello "+data);
                }

            }
            @Override public void onCancelled(FirebaseError error) { }
        });


        np = (NumberPicker) findViewById(R.id.numberPicker);
        Random rand = new Random();
        final int randomVal = rand.nextInt(20);
        String[] nums = new String[20];
        for(int i=0; i<nums.length; i++)
            nums[i] = Integer.toString(i);

        np.setMinValue(1);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);
        //np.setDisplayedValues(nums);
        np.setValue(1);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    if (newVal == randomVal) {
                        textView.setText("You got it");
                        myFirebaseRef.child("values").setValue(newVal);
                    }

            }
        });

    }
}
