package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 123;
    public static ArrayList<String> words = new ArrayList<String>();
    public static ArrayList<String> currentWords = new ArrayList<String>();
    public static ArrayList<Player> playerList = new ArrayList<Player>();
//    public static Player[] playerWievList = new Player[]{new Player(), new Player(), new Player()};

    static private ListView wordList;

    TextView wordView, rightIteratorView, wrongIteratorView, chronometer;
    Button skipBtn, rightBtn,startBtn , restartBtn, playerListBtn;

    boolean endTime = false;

    View startLayout,gameLayout, currentWordLayout;

    Integer currentNumber = 0;

    CountDownTimer timer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            chronometer.setText("" + (millisUntilFinished/1000+1));
        }

        @Override
        public void onFinish() {
            nextPlayer(playerList.size());
//            wordView.setText(playerList.get(currentNumber).getName());
            rightIteratorView.setText(String.valueOf(playerList.get(currentNumber).getScore()));
            chronometer.setText("Последнее слово");
            endTime = true;
        }
    };

    static int wordIterator = 0;
    static int right = 0, wrong = 0;

//    public MainActivity() throws IOException, ClassNotFoundException {
//    }
        @Override
        protected void onCreate (Bundle savedInstanceState){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE);

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            wordView = findViewById(R.id.textView1);
            rightIteratorView = findViewById(R.id.textViewRight);
//            wrongIteratorView = findViewById(R.id.textViewWrong);
            chronometer = findViewById(R.id.chronometer);

            skipBtn = findViewById(R.id.skip);
            rightBtn = findViewById(R.id.right);
            restartBtn = findViewById(R.id.restart);
            startBtn = findViewById(R.id.startBtn);
            playerListBtn = findViewById(R.id.playerListBtn);

            wordList = findViewById(R.id.wordList);

            startLayout = findViewById(R.id.startLayout);
            startLayout.setVisibility(View.VISIBLE);
            gameLayout = findViewById(R.id.gameLayout);
            currentWordLayout = findViewById(R.id.currentWordLayout);

            playerList.add(new Player());
            playerList.add(new Player());
            playerList.add(new Player());


//            wordView.setText(playerList.get(currentNumber).getName());

            FileInputStream fis = null;

            try {
                fis = openFileInput("Zalupa32.xml");
            } catch (FileNotFoundException e) {
                wordView.setText(e.toString());
            }

            XMLHandler handler;
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = null;
            try {
                parser = factory.newSAXParser();
                handler = new XMLHandler();
                parser.parse(fis, handler);
            } catch (Exception e) {
//                e.printStackTrace();
                wordView.setText(e.toString());
            }

            Collections.shuffle(words);

            wordView.setText(words.get(wordIterator));
        }


        public void nextWord (View view) throws IOException {

            TextView wordView = findViewById(R.id.textView1);

            try {
                wordView.setText(words.get(wordIterator++));
            }
            catch (Exception e){
                wordView.setText(e.toString());
            }
        }

    public void restartWord(View view) {
        wordIterator = 0;
        right = 0;
        wrong = 0;

        rightIteratorView.setText(String.valueOf(right));
        wrongIteratorView.setText(String.valueOf(wrong));

        Collections.shuffle(words);

        try {
            wordView.setText(words.get(wordIterator++));
        }
        catch (Exception e){
            wordView.setText(e.toString());
        }
    }


    public void right(View view){
        playerList.get(currentNumber).upScore();
        rightIteratorView.setText(String.valueOf(playerList.get(currentNumber).getScore()));
        wordView.setText(words.get(++wordIterator));
        if (endTime)
        {
            gameLayout.setVisibility(View.GONE);
            currentWordLayout.setVisibility(View.VISIBLE);
            ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currentWords);
            wordList.setAdapter(adapter);
        };
    }

    public void contin (View view){

        currentWordLayout.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);

        currentWords.clear();
        timer.start();

    }

    public void skip(View view) {
        currentWords.add(words.get(wordIterator++));
        wordView.setText(words.get(wordIterator));
    }

    public void start(View view) {
//            startBtn.setVisibility(View.INVISIBLE);
//            restartBtn.setVisibility(View.VISIBLE);
//            skipBtn.setVisibility(View.VISIBLE);
//            rightBtn.setVisibility(View.VISIBLE);
//            chronometer.setVisibility(View.VISIBLE);
//            nextPlayer(playerList.size());
//            wordView.setText(playerList.get(currentNumber).getName());
//            playerListBtn.setVisibility(View.VISIBLE);
//        intermediateLayout.setVisibility(View.GONE)
        startLayout.setVisibility(View.GONE);
        gameLayout.setVisibility(View.VISIBLE);
        currentWords.clear();
        timer.start();
    }

    public void playerListView(View view) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }

    public void nextPlayer(int numberOfPlayer){
            if (currentNumber < numberOfPlayer-1) currentNumber ++;
            else currentNumber = 0;
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public void nextPlayerTest(View view) {
        try {
            nextPlayer(playerList.size());
            wordView.setText(playerList.get(currentNumber).getName());
        } catch (Exception e) {
            wordView.setText(e.toString());

        }
    }

//    public void currentWordsDrow(){
//                ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.word_list_item, currentWords);
//                wordList.setAdapter(adapter);
//    }
}