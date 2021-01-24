package com.example.smsbomba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

public class Splawsh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splawsh);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(Splawsh.this, MainActivity.class);
                Splawsh.this.startActivity(mainIntent);
                Splawsh.this.finish();
            }
        }, secondsDelayed * 1000);
    }
}


