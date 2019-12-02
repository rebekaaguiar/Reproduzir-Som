package com.rebekaaguiar.prj_android_extra10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

/*Aluna: Rebeka Ramos de Aguiar Silva
Professor: Renan Costa Alencar
Data: 25/11/2019*/


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_Brasil;
    private Button btn_Franca;
    private Button btn_Argentina;
    private Button btn_Mexico;
    private Button btn_Eua;
    private Button btn_Alemanha;
    private Button btn_sair;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = new MediaPlayer();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });

        btn_Brasil = (Button) findViewById(R.id.btn_Brasil);
        btn_Franca = (Button) findViewById(R.id.btn_Franca);
        btn_Argentina = (Button) findViewById(R.id.btn_Argentina);
        btn_Mexico = (Button) findViewById(R.id.btn_Mexico);
        btn_Eua = (Button) findViewById(R.id.btn_Eua);
        btn_Alemanha = (Button) findViewById(R.id.btn_Alemanha);

        btn_Brasil.setOnClickListener(this);
        btn_Franca.setOnClickListener(this);
        btn_Argentina.setOnClickListener(this);
        btn_Mexico.setOnClickListener(this);
        btn_Eua.setOnClickListener(this);
        btn_Alemanha.setOnClickListener(this);

        btn_sair = (Button)findViewById(R.id.btn_sair);
        btn_sair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
    @Override
    public void onClick(View v) {
        try {
            if (mp.isPlaying()) {
                mp.stop();
            }
            mp.reset();
            AssetFileDescriptor afd = null;
            switch (v.getId()) {
                case R.id.btn_Brasil:
                    Toast.makeText(this, "Tocando o hino do Brasil!", Toast.LENGTH_SHORT).show();
                    afd = getResources().openRawResourceFd(R.raw.brasil);
                    break;
                case R.id.btn_Franca:
                    Toast.makeText(this, "Tocando o hino da França!", Toast.LENGTH_SHORT).show();
                    afd = getResources().openRawResourceFd(R.raw.franca);
                    break;
                case R.id.btn_Argentina:
                    Toast.makeText(this, "Tocando o hino da Argentina!", Toast.LENGTH_SHORT).show();
                    afd = getResources().openRawResourceFd(R.raw.argentina);
                    break;
                case R.id.btn_Mexico:
                    Toast.makeText(this, "Tocando o hino do México!", Toast.LENGTH_SHORT).show();
                    afd = getResources().openRawResourceFd(R.raw.mexico);
                    break;
                case R.id.btn_Eua:
                    Toast.makeText(this, "Tocando o hino dos Estados Unidos!", Toast.LENGTH_SHORT).show();
                    afd = getResources().openRawResourceFd(R.raw.eua);
                    break;
                case R.id.btn_Alemanha:
                    Toast.makeText(this, "Tocando o hino da Alemanha!", Toast.LENGTH_SHORT).show();
                    afd = getResources().openRawResourceFd(R.raw.alemanha);
                    break;
            }
            if (afd != null) {
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepareAsync();
            }
        } catch (IOException e) {
            Log.e("", e.getMessage());
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
        mp.release();
    }
}