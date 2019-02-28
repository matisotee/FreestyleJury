package com.example.matisotee.freestylejury.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.matisotee.freestylejury.R;
import com.example.matisotee.freestylejury.models.DTOInfoBatalla;
import com.example.matisotee.freestylejury.models.ExpertoCrearBatalla;
import com.example.matisotee.freestylejury.models.Player;
import com.example.matisotee.freestylejury.models.Round;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BattleActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView textViewNumberRound;
    private TextView textViewPlayer;
    private TextView textViewPatronesRestantes;
    private ImageButton ibMasUno;
    private ImageButton ibMasDos;
    private ImageButton ibMasTres;
    private ImageButton ibMasCuatro;
    private ImageButton ibMasCinco;

    private ExpertoCrearBatalla experto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        this.enforceIconBar();

        textViewNumberRound = (TextView)findViewById(R.id.textViewNumberRound);
        textViewPlayer = (TextView)findViewById(R.id.textViewPlayer);
        ibMasUno= (ImageButton)findViewById(R.id.imageButtonMasUno);
        ibMasDos= (ImageButton)findViewById(R.id.imageButtonMasDos);
        ibMasTres= (ImageButton)findViewById(R.id.imageButtonMasTres);
        ibMasCuatro= (ImageButton)findViewById(R.id.imageButtonMasCuatro);
        ibMasCinco= (ImageButton)findViewById(R.id.imageButtonMasCinco);
        textViewPatronesRestantes=(TextView)findViewById(R.id.textViewPatronesRestantes);



        //Tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        experto = (ExpertoCrearBatalla) bundle.getSerializable("experto");


        Picasso.with(this).load(R.drawable.mas_uno).fit().into(ibMasUno);
        Picasso.with(this).load(R.drawable.mas_dos).fit().into(ibMasDos);
        Picasso.with(this).load(R.drawable.mas_tres).fit().into(ibMasTres);
        Picasso.with(this).load(R.drawable.mas_cuatro).fit().into(ibMasCuatro);
        Picasso.with(this).load(R.drawable.mas_cinco).fit().into(ibMasCinco);

        //Empezar Batalla
        DTOInfoBatalla dto= experto.iniciarBatalla();
        actualizarPantalla(dto);



        ibMasUno.setOnClickListener(this);

        ibMasDos.setOnClickListener(this) ;

        ibMasTres.setOnClickListener(this);

        ibMasCuatro.setOnClickListener(this);

        ibMasCinco.setOnClickListener(this);


    }

    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.drawable.app_tittle);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public void onClick(View v) {

        boolean terminarRound;
        boolean terminarBatalla;
        DTOInfoBatalla dto;
        switch (v.getId()){
            case R.id.imageButtonMasUno:
                dto =experto.votar(1);
                break;
            case R.id.imageButtonMasDos:
                dto =experto.votar(2);
                break;
            case R.id.imageButtonMasTres:
                dto =experto.votar(3);
                break;
            case R.id.imageButtonMasCuatro:
                dto=experto.votar(4);
                break;
            case R.id.imageButtonMasCinco:
                dto=experto.votar(5);
                break;
            default:
                dto=null;
                terminarRound=false;
        }
        terminarRound = dto.getTerminarRound();
        terminarBatalla= dto.getTerminarBatalla();

        if(terminarBatalla){

            Intent intent = new Intent(BattleActivity.this,PuntuationActivity.class);

            intent.putExtra("experto",experto);
            startActivity(intent);


        }else if (terminarRound && (!terminarBatalla)){
            showAlertForFinishRound();
            dto=experto.cambiarRound();
        }
        actualizarPantalla(dto);
    }

    private void actualizarPantalla(DTOInfoBatalla dto ){
        textViewNumberRound.setText("Round "+(dto.getRound()+1)+" : "+dto.getTiempo()+" "+dto.getModalidad());
        textViewPatronesRestantes.setText("Patrones restantes: "+dto.getPatronesRestantes());
        textViewPlayer.setText("Turno: "+dto.getTurno());
    }

    private void showAlertForFinishRound() {

        Vibrator vibrator = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(600);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_cambiar_round, null);
        builder.setView(viewInflated);


        builder.setPositiveButton("Siguiente Ronund", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);

    }
}
