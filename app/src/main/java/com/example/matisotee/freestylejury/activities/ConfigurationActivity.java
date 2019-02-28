package com.example.matisotee.freestylejury.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matisotee.freestylejury.R;
import com.example.matisotee.freestylejury.adapters.RadioButtonAdapter;
import com.example.matisotee.freestylejury.models.DTOBatalla;
import com.example.matisotee.freestylejury.models.DTOPreferencias;
import com.example.matisotee.freestylejury.models.DTORound;
import com.example.matisotee.freestylejury.models.ExpertoCrearBatalla;
import com.example.matisotee.freestylejury.models.Modalidad;
import com.example.matisotee.freestylejury.models.Player;
import com.example.matisotee.freestylejury.models.Round;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationActivity extends AppCompatActivity {

    //constantes
    private final static int MAX_OF_ROUNDS = 7;


    //Manejo de datos
    private ArrayList<String> players = new ArrayList<>();

    private DTOBatalla dtoBatalla = new DTOBatalla();
    private ExpertoCrearBatalla experto = new ExpertoCrearBatalla();
    private DTOPreferencias dtoPreferencias = experto.obtenerPreferencias();
    private ArrayList<DTORound> DTORounds = new ArrayList<>();

    private final String DEFAULT_MODALIDAD = dtoPreferencias.getModalidades().get(0);
    private final String DEFAULT_TIEMPO = dtoPreferencias.getTiempos().get(0);


    RadioButtonAdapter adapterEmpieza = new RadioButtonAdapter(R.layout.modalidad_list_view_item,players,ConfigurationActivity.this,0);


    //Elementos UI
    private ImageButton imageButtonRounds;
    private ImageButton imageButtonModalidad;
    private ImageButton imageButtonEmpieza;
    private Button buttonBatalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_configuration);

        this.enforceIconBar();

        imageButtonRounds = findViewById(R.id.imageButtonRounds);
        imageButtonModalidad = findViewById(R.id.imageButtonModalidad);
        imageButtonEmpieza = findViewById(R.id.imageButtonEmpieza);
        buttonBatalla = findViewById(R.id.buttonBatalla);


        Picasso.with(this).load(R.drawable.un_round).fit().into(imageButtonRounds);
        Picasso.with(this).load(R.drawable.modalidad).fit().into(imageButtonModalidad);
        Picasso.with(this).load(R.drawable.quien_empieza).fit().into(imageButtonEmpieza);

        //Tomar los datos del intent
        Bundle bundle = getIntent().getExtras();
        int cantidad = bundle.getInt("cantidad");
        for (int i = 0; i < cantidad; i++) {
            String player = bundle.getString("id" + i);
            players.add(player);
        }

        //Lleno el dto con valores por defecto
        dtoBatalla.setPlayers(players);
        dtoBatalla.setEmpieza(0);
        dtoBatalla.setRounds(DTORounds);
        DTORounds.add(new DTORound(DEFAULT_MODALIDAD,DEFAULT_TIEMPO,0,0));






        imageButtonRounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForAddRounds();
            }
        });

        imageButtonModalidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = (DTORounds.size() - 1); i >= 0; i--) {
                    showAlertForSelectModalidad(i);
                }
            }
        });

        imageButtonEmpieza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertForSelectEmpieza();
            }
        });

        buttonBatalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfigurationActivity.this,BattleActivity.class);
                experto.configurarBatalla(dtoBatalla);
                intent.putExtra("experto",experto);
                startActivity(intent);
            }
        });


    }

    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.drawable.app_tittle);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void showAlertForAddRounds() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_rounds, null);
        builder.setView(viewInflated);

        final TextView cantRounds = (TextView) viewInflated.findViewById(R.id.textViewCantidadRounds);
        final SeekBar roundSB = (SeekBar) viewInflated.findViewById(R.id.seekBarRounds);

        roundSB.setMax(MAX_OF_ROUNDS);


        roundSB.setProgress(DTORounds.size()-1);



        cantRounds.setText("" + (roundSB.getProgress() + 1));

        roundSB.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    int progress_value;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progress_value = progress;
                        cantRounds.setText("" + (roundSB.getProgress() + 1));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        cantRounds.setText("" + (roundSB.getProgress() + 1));
                    }
                }
        );


        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int cantidadRounds = (roundSB.getProgress() + 1);
                configurarRounds(cantidadRounds);
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);

    }

    private void configurarRounds(int cantidad) {
        if (cantidad > DTORounds.size()) {
            for (int i = (cantidad - DTORounds.size()); i > 0; i--) {
                DTORounds.add(new DTORound(DEFAULT_MODALIDAD,DEFAULT_TIEMPO,0,0));
            }
        } else if (cantidad < DTORounds.size()) {
            for (int i = (DTORounds.size() - cantidad); i > 0; i--) {
                DTORounds.remove((DTORounds.size() - 1));
            }
        }

        switch (cantidad){
            case 1:
                Picasso.with(this).load(R.drawable.un_round).fit().into(imageButtonRounds);
                break;
            case 2:
                Picasso.with(this).load(R.drawable.dos_rounds).fit().into(imageButtonRounds);
                break;
            case 3:
                Picasso.with(this).load(R.drawable.tres_rounds).fit().into(imageButtonRounds);
                break;
            case 4:
                Picasso.with(this).load(R.drawable.cuatro_rounds).fit().into(imageButtonRounds);
                break;
            case 5:
                Picasso.with(this).load(R.drawable.cinco_rounds).fit().into(imageButtonRounds);
                break;
            case 6:
                Picasso.with(this).load(R.drawable.seis_rounds).fit().into(imageButtonRounds);
                break;
            case 7:
                Picasso.with(this).load(R.drawable.siete_rounds).fit().into(imageButtonRounds);
                break;
            case 8:
                Picasso.with(this).load(R.drawable.ocho_rounds).fit().into(imageButtonRounds);
                break;
        }
    }

    private void showAlertForSelectModalidad(final int numberRound) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_modalidad, null);
        builder.setView(viewInflated);

        final TextView titulo = (TextView) viewInflated.findViewById(R.id.textViewRoundTitleRound);
        ListView listViewTiempo = (ListView) viewInflated.findViewById(R.id.listViewTiempo);
        ListView listViewModalidad = (ListView) viewInflated.findViewById(R.id.listViewModalidad);

        final DTORound currentDTORound = DTORounds.get(numberRound);

        titulo.setText("Round " + (numberRound + 1));



        final RadioButtonAdapter adapterTiempo = new RadioButtonAdapter(R.layout.modalidad_list_view_item,dtoPreferencias.getTiempos(),ConfigurationActivity.this,DTORounds.get(numberRound).getPosicionTiempo());
        final RadioButtonAdapter adapterModalidad = new RadioButtonAdapter(R.layout.modalidad_list_view_item,dtoPreferencias.getModalidades(),ConfigurationActivity.this,DTORounds.get(numberRound).getPosicionModalidad());



        listViewTiempo.setAdapter(adapterTiempo);
        listViewModalidad.setAdapter(adapterModalidad);

        listViewTiempo.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listViewModalidad.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String modalidadElegida = dtoPreferencias.getModalidades().get(adapterModalidad.getPositionChecked());
                String tiempoElegido = dtoPreferencias.getTiempos().get(adapterTiempo.getPositionChecked());
                currentDTORound.setModalidad(modalidadElegida);
                currentDTORound.setTiempo(tiempoElegido);
                currentDTORound.setPosicionModalidad(adapterModalidad.getPositionChecked());
                currentDTORound.setPosicionTiempo(adapterTiempo.getPositionChecked());
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);

    }

    private void showAlertForSelectEmpieza() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_empieza, null);
        builder.setView(viewInflated);

        final TextView tittleEmpieza = (TextView) viewInflated.findViewById(R.id.textViewTittleEmpieza);
        ListView listViewEmpieza = (ListView) viewInflated.findViewById(R.id.listViewEmpieza);

        tittleEmpieza.setText("Seleccione quien empieza");

        listViewEmpieza.setAdapter(adapterEmpieza);
        listViewEmpieza.setChoiceMode(ListView.CHOICE_MODE_SINGLE);




        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dtoBatalla.setEmpieza(adapterEmpieza.getPositionChecked());
            }


        });

        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.fms:
                configurarFms();
                Toast.makeText(ConfigurationActivity.this,"Formato FMS configurado",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void configurarFms(){
        configurarRounds(8);

        DTORounds.get(0).setTiempo("minuto");
        DTORounds.get(0).setPosicionTiempo(0);
        DTORounds.get(0).setModalidad("Libre");
        DTORounds.get(0).setPosicionModalidad(2);

        DTORounds.get(1).setTiempo("minuto");
        DTORounds.get(1).setPosicionTiempo(0);
        DTORounds.get(1).setModalidad("Libre");
        DTORounds.get(1).setPosicionModalidad(2);

        DTORounds.get(2).setTiempo("40 segundos");
        DTORounds.get(2).setPosicionTiempo(1);
        DTORounds.get(2).setModalidad("Libre");
        DTORounds.get(2).setPosicionModalidad(2);

        DTORounds.get(3).setTiempo("40 segundos");
        DTORounds.get(3).setPosicionTiempo(1);
        DTORounds.get(3).setModalidad("Libre");
        DTORounds.get(3).setPosicionModalidad(2);

        DTORounds.get(4).setTiempo("minuto");
        DTORounds.get(4).setPosicionTiempo(0);
        DTORounds.get(4).setModalidad("4 x 4");
        DTORounds.get(4).setPosicionModalidad(0);

        DTORounds.get(5).setTiempo("minuto");
        DTORounds.get(5).setPosicionTiempo(0);
        DTORounds.get(5).setModalidad("Libre");
        DTORounds.get(5).setPosicionModalidad(2);

        DTORounds.get(6).setTiempo("minuto");
        DTORounds.get(6).setPosicionTiempo(0);
        DTORounds.get(6).setModalidad("Libre");
        DTORounds.get(6).setPosicionModalidad(2);

        DTORounds.get(7).setTiempo("9 entradas");
        DTORounds.get(7).setPosicionTiempo(2);
        DTORounds.get(7).setModalidad("4 x 4");
        DTORounds.get(7).setPosicionModalidad(0);


    }




}
