package com.example.matisotee.freestylejury.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.matisotee.freestylejury.R;
import com.example.matisotee.freestylejury.models.Player;

import java.util.List;

public class PlayerAdapter extends BaseAdapter {
    private int layout;
    private List<String> players;
    private Context context;
    private static int counter = 0;

    public PlayerAdapter(int layout, List<String> players, Context context) {
        this.layout = layout;
        this.players = players;
        this.context = context;
    }

    @Override
    public int getCount() {
        return players.size();
    }

    @Override
    public Object getItem(int position) {
        return players.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.player_list_view_item,null);
            vh = new ViewHolder();
            vh.playerName = (TextView) convertView.findViewById(R.id.texViewPlayerName);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder)convertView.getTag();
        }

        String player = players.get(position);
        vh.playerName.setText("Player " +(++position) +" : "+ player);


        return convertView;
    }


    public class ViewHolder{
        TextView playerName;
    }
}
