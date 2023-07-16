package com.example.tictoytac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String getplayeronename , getplayertwoname;
    int rounds;
    private int  playeronescorecount , playertwoscorecount;

    private TextView playeronescore , playertwoscore , playerstatus , textplayer1 , textplayer2;
    private Button[] button = new Button[9];
    private Button reset , playagain;
    boolean playeroneactive;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    int[][] winingposition = {{0,1,2} , {3,4,5} , {6,7,8} , {0,3,6} , {1,4,7} , {2,5,8} , {0,4,8} , {2,4,6} };

    public MainActivity() {
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playeronescore = findViewById(R.id.scoreplayer1);
        playertwoscore = findViewById(R.id.scoreplayer2);
        playerstatus = findViewById(R.id.textStatus);
        reset = findViewById(R.id.textresetgame);
        playagain = findViewById(R.id.textplayagain);
        textplayer1 = findViewById(R.id.textplayer1);
        textplayer2 = findViewById(R.id.textplayer2);

        button[0] = findViewById(R.id.btn0);
        button[1] = findViewById(R.id.btn1);
        button[2] = findViewById(R.id.btn2);
        button[3] = findViewById(R.id.btn3);
        button[4] = findViewById(R.id.btn4);
        button[5] = findViewById(R.id.btn5);
        button[6] = findViewById(R.id.btn6);
        button[7] = findViewById(R.id.btn7);
        button[8] = findViewById(R.id.btn8);

        getplayeronename = getIntent().getStringExtra("playeronename");
        getplayertwoname = getIntent().getStringExtra("playertwoname");

        textplayer1.setText(getplayeronename);
        textplayer2.setText(getplayertwoname);

        for (int i=0 ; i<button.length ; i++){
            button[i].setOnClickListener(this);
        }

        playeronescorecount = 0;
        playertwoscorecount = 0;
        playeroneactive = true;
        boolean rounds = true;

    }

    @Override
    public void onClick(View view) {

        if (!((Button)view).getText().toString().equals("")){
            return;
        } else if (checkwinner()) {
            return;
        }
        String buttonid =  view.getResources().getResourceEntryName(view.getId());
        int gamestatepointer = Integer.parseInt(buttonid.substring(buttonid.length()-1,buttonid.length()));

        if (playeroneactive){
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#70fc3a"));
            gamestate[gamestatepointer] = 0;
        }
        else {
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gamestate[gamestatepointer] = 1;
        }
        rounds++;

        if (checkwinner()){
            if (playeroneactive){
                playeronescorecount++;
                updateplayerscore();
                playerstatus.setText(getplayeronename + "  win");
            }
            else {
                playertwoscorecount++;
                updateplayerscore();
                playerstatus.setText(getplayertwoname + "  win");
            }
        } else if (rounds == 9) {
            playerstatus.setText("no Winner");
        }
        else {
            playeroneactive = !playeroneactive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playagain();
                playeronescorecount = 0;
                playertwoscorecount = 0;
                updateplayerscore();
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playagain();
            }
        });
    }

    private boolean checkwinner() {
        boolean winnerresult = false;
        for (int[] winingposition : winingposition){
            if (gamestate[winingposition[0]] == gamestate[winingposition[1]]  &&  gamestate[winingposition[1]] == gamestate[winingposition[2]]  &&  gamestate[winingposition[1]]!=2){
                winnerresult = true;
            }
        }
        return winnerresult;
    }

    private void playagain() {
        playeroneactive =true;
        for (int i = 0 ; i< button.length ; i++){
            gamestate[i] = 2;
            button[i].setText("");
        }
        playerstatus.setText("Status");
    }

    private void updateplayerscore() {
        playertwoscore.setText(Integer.toString(playertwoscorecount));
        playeronescore.setText(Integer.toString(playeronescorecount));

    }
}