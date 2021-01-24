package com.example.smsbomba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //deklaracija globalnih spremenljivk
    private Timer Bomba;
    String telSt;
    String sporocilo;
    int steviloSporocil;
    SmsManager smsManager = SmsManager.getDefault();
    int stevilo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button stop = (Button) findViewById(R.id.stopButton);
        stop.setVisibility(View.INVISIBLE);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SEND_SMS},
                1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.about){
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    finish();
                    System.exit(0);

                    //Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    public void sendMessage(View view) {
        EditText sporociloPolje = (EditText) findViewById(R.id.VnosSporocilo);
        EditText bombaPolje = (EditText) findViewById(R.id.BombaSt);
        EditText telefonskaPolje = (EditText) findViewById(R.id.Telefonska);
        Button stop = (Button) findViewById(R.id.stopButton);
        TextView MessageCounter = findViewById(R.id. numMsgCounter);

        MessageCounter.setText("0");
        stevilo = 0;
        telSt = telefonskaPolje.getText().toString();
        sporocilo = sporociloPolje.getText().toString();
        steviloSporocil = Integer.parseInt(bombaPolje.getText().toString());


        stop.setVisibility(View.VISIBLE);
        Bomba = new Timer();
        Bomba.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                smsManager.sendTextMessage(telSt, null, sporocilo, null, null);
                stevilo++;
                MessageCounter.setText(stevilo + "");

                if(stevilo==steviloSporocil && steviloSporocil != 0)
                    Bomba.cancel();
            }
        } ,0,300);
    }
    public void cancel(View view) {
        Bomba.cancel();
    }
}