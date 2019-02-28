package com.example.matisotee.freestylejury.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.matisotee.freestylejury.R;
import com.example.matisotee.freestylejury.models.DTOPuntuaciones;
import com.example.matisotee.freestylejury.models.ExpertoCrearBatalla;
import com.example.matisotee.freestylejury.models.Player;

import java.util.ArrayList;
import java.util.List;

public class PuntuationActivity extends AppCompatActivity {


    private TextView namePlayerOne;
    private TextView namePlayerTwo;
    private TextView puntuationPlayerOne;
    private TextView puntuationPlayerTwo;
    private Button buttonFinBatalla;
    ExpertoCrearBatalla experto;
    ArrayList<DTOPuntuaciones> puntuaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuation);

        this.enforceIconBar();


        namePlayerOne = (TextView)findViewById(R.id.textViewNamePlayerOne);
        namePlayerTwo = (TextView)findViewById(R.id.textViewNamePlayerTwo);
        puntuationPlayerOne = (TextView)findViewById(R.id.textViewPuntuationPlayerOne);
        puntuationPlayerTwo = (TextView)findViewById(R.id.textViewPuntuationPlayerTwo);
        buttonFinBatalla = (Button)findViewById(R.id.buttonFinBatalla);


        Bundle bundle = getIntent().getExtras();
        experto = (ExpertoCrearBatalla) bundle.getSerializable("experto");

        puntuaciones = experto.terminarBatalla();


        namePlayerOne.setText(puntuaciones.get(0).getPlayer()+" :");
        namePlayerTwo.setText(puntuaciones.get(1).getPlayer()+" :");
        puntuationPlayerOne.setText(""+puntuaciones.get(0).getPuntuacion());
        puntuationPlayerTwo.setText(""+puntuaciones.get(1).getPuntuacion());

        buttonFinBatalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PuntuationActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.drawable.app_tittle);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
