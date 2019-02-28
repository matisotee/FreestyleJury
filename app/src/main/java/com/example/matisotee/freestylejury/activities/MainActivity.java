package com.example.matisotee.freestylejury.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.matisotee.freestylejury.R;
import com.example.matisotee.freestylejury.adapters.PlayerAdapter;
import com.example.matisotee.freestylejury.models.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button buttonBatallar;
    private FloatingActionButton fab;
    private static BaseAdapter adapter;
    private List<String> players = new ArrayList<String>();
    private ListView listView;
    private final static int PLAYERS_LIMIT = 2;
    private final static int PLAYERS_MIN = 2;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.enforceIconBar();
        buttonBatallar = (Button) findViewById(R.id.buttonBatallar);
        fab = (FloatingActionButton)findViewById(R.id.fabAddPlayer);
        listView = (ListView) findViewById(R.id.listViewPlayer);


        adapter = new PlayerAdapter(R.layout.player_list_view_item, players,this);
        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter< PLAYERS_LIMIT){
                    showAlertForCreatingPlayer();
                }else{
                    limitReached();
                }

            }
        });

        buttonBatallar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<PLAYERS_MIN){
                    Toast.makeText(MainActivity.this,"Debe agregar freestylers",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(MainActivity.this,ConfigurationActivity.class);
                    for (int i =0; i<players.size();i++){
                        intent.putExtra("id"+i,players.get(i));
                    }
                    intent.putExtra("cantidad",players.size());
                    startActivity(intent);

                }

            }
        });


    }

    private void enforceIconBar() {
        getSupportActionBar().setIcon(R.drawable.app_tittle);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    private void limitReached(){
        Toast.makeText(this,"Se alcanzo el limite de freestylers",Toast.LENGTH_SHORT).show();
    }



    private void showAlertForCreatingPlayer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_create_player, null);
        builder.setView(viewInflated);

        final EditText input = (EditText) viewInflated.findViewById(R.id.editTextNewPlayer);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String playerName = input.getText().toString().trim();


                if(playerName.length() > 0){
                    addPlayer(playerName);
                    counter++;
                }else{
                    Toast.makeText(getApplicationContext(),"The name is required to create a new Board", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Dialog dialog =builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);

    }

    public void addPlayer(String player){
        players.add(player);
        adapter.notifyDataSetChanged();
    }


}
