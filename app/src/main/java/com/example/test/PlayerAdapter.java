package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private int layout;
    private List<Player> players;

    public PlayerAdapter(@NonNull Context context, int resource, List<Player> players) {
        super(context, resource, players);
        this.players = players;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }



    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if(convertView==null){
            convertView = inflater.inflate(this.layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Player player = players.get(position);

        viewHolder.teamName.setText(player.getName());

        viewHolder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.playerList.remove(position);
                PlayerActivity.playerListDraw();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        final Button delBtn;
        final TextView teamName;
        ViewHolder(View view){
            teamName = view.findViewById(R.id.teamName);
            delBtn = view.findViewById(R.id.delBtn);
        }
    }

}
