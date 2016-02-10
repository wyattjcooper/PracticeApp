package com.example.wyattcooper.practiceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.NumberPicker;
import java.util.Random;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
public class MainActivity extends AppCompatActivity {

    TextView textView;
    NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        final Firebase myFirebaseRef = new Firebase("https://dazzling-inferno-9759.firebaseio.com/");
        myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");

        textView = (TextView) findViewById(R.id.textView);

        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();
                textView.setText(data);
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
