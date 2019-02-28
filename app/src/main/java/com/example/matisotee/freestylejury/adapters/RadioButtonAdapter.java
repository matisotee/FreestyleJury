package com.example.matisotee.freestylejury.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.matisotee.freestylejury.R;

import java.util.List;


public class RadioButtonAdapter extends BaseAdapter{

    private int layout;
    private List<String> textos;
    private Context context;
    private static int counter = 0;
    private int positionChecked;

    public RadioButtonAdapter(int layout, List<String> textos, Context context, int positionChecked) {
        this.layout = layout;
        this.textos = textos;
        this.context = context;
        this.positionChecked = positionChecked;
    }

    @Override
    public int getCount() {
        return textos.size();
    }

    @Override
    public Object getItem(int position) {
        return textos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RadioButtonAdapter.ViewHolder vh;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout,null);
            vh = new RadioButtonAdapter.ViewHolder();
            vh.rButton =  convertView.findViewById(R.id.rButtonModalidad);
            convertView.setTag(vh);
        }else{
            vh = (RadioButtonAdapter.ViewHolder)convertView.getTag();
        }

        String texto = textos.get(position);
        vh.rButton.setText(texto);
        vh.rButton.setChecked(position == positionChecked);
        vh.rButton.setTag(position);
        vh.rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                positionChecked= (Integer)v.getTag();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    public class ViewHolder{
        RadioButton rButton;
    }


    public int getPositionChecked() {
        return positionChecked;
    }

    public void setPositionChecked(int positionChecked) {
        this.positionChecked = positionChecked;
    }
}
