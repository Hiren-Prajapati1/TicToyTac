package com.example.tictoytac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPlayername extends AppCompatActivity {

    EditText player1editname , player2editname;
    Button startgamebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_playername);

        player1editname = findViewById(R.id.player1editname);
        player2editname = findViewById(R.id.player2editname);
        startgamebtn = findViewById(R.id.startgamebtn);

        startgamebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String getplayeronename = player1editname.getText().toString();
                final String getplayertwoname = player2editname.getText().toString();

                if (getplayeronename.isEmpty() || getplayertwoname.isEmpty()){
                    Toast.makeText(AddPlayername.this, "Enter player names", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(AddPlayername.this , MainActivity.class);
                    intent.putExtra("playeronename" , getplayeronename);
                    intent.putExtra("playertwoname" , getplayertwoname);
                    startActivity(intent);
                }

            }
        });

    }
}