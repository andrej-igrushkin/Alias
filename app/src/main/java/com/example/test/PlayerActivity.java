package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class PlayerActivity extends AppCompatActivity {
    static private ListView playerList;
    static private Context thisContext;

    private ConstraintLayout newTeamCreateLayout;
    private Button createNewTeamBtn, acceptNewTeamBtn;
    private EditText newTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        playerList = (ListView) findViewById(R.id.playerList);
        thisContext = this;

        newTeamCreateLayout = findViewById(R.id.newTeamCreateLayout);

        createNewTeamBtn = findViewById(R.id.createNewTeamBtn);
        acceptNewTeamBtn = findViewById(R.id.acceptNewTeamBtn);

        newTeamName = findViewById(R.id.newTeamName);

        playerListDraw();
    }


    static public void playerListDraw(){
        PlayerAdapter playerAdapter = new PlayerAdapter(thisContext, R.layout.player_list_item, MainActivity.playerList);

        playerList.setAdapter(playerAdapter);
    }

    public void test(View view) {
        playerListDraw();
    }

    public void teamCreate(View view) {
        newTeamCreateLayout.setVisibility(View.VISIBLE);
        createNewTeamBtn.setVisibility(View.INVISIBLE);
    }

    public void acceptName(View view) {
        MainActivity.playerList.add(new Player(newTeamName.getText().toString()));

        newTeamCreateLayout.setVisibility(View.INVISIBLE);
        newTeamName.setText("");

        createNewTeamBtn.setVisibility(View.VISIBLE);

        MainActivity.hideSoftKeyboard(this);

        playerListDraw();
    }



}