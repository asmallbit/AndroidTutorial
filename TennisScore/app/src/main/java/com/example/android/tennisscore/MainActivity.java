package com.example.android.tennisscore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Seems like we can only declare a global variable if we dont we it be final:<
    int teamAScoreS, teamBScoreS, teamAScoreL, teamBScoreL;

    TextView teamAS, teamBS, teamAL, teamBL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button teamAbtn, teamBbtn, resetbtn;

        teamAScoreS = teamBScoreS = teamAScoreL = teamBScoreL = 0;

        teamAbtn = findViewById(R.id.teamAbtn);
        teamBbtn = findViewById(R.id.teamBbtn);
        resetbtn = findViewById(R.id.resetbtn);
        teamAS = findViewById(R.id.teamAS);
        teamAL = findViewById(R.id.teamAL);
        teamBS = findViewById(R.id.teamBS);
        teamBL = findViewById(R.id.teamBL);

        teamAbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if a team won then dont click this button
                if(isNextRound(teamAScoreL, teamBScoreL) != 0){
                    return;
                }
                // Score added
                teamAScoreS++;
                teamAS.setText(Integer.toString(teamAScoreS));
                nextRound(teamAScoreS, teamBScoreS);
                // game over?
                isEnd(teamAScoreL, teamBScoreL);
            }
        });

        teamBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if a team won then dont click this button
                if(isNextRound(teamAScoreL, teamBScoreL) != 0){
                    return;
                }
                // Score added
                teamBScoreS++;
                teamBS.setText(Integer.toString(teamBScoreS));
                nextRound(teamAScoreS, teamBScoreS);
                // game over?
                isEnd(teamAScoreL, teamBScoreL);
            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamAScoreL = teamBScoreL = teamAScoreS = teamBScoreS = 0;
                teamAL.setText(Integer.toString(teamAScoreL));
                teamBL.setText(Integer.toString(teamBScoreL));
                teamAS.setText(Integer.toString(teamAScoreS));
                teamBS.setText(Integer.toString(teamBScoreS));
            }
        });
    }

    public int isNextRound(int x, int y){
        // A win or B win? We will return 1 if B win, return -1 if A win and return 0 if neither.
        if(x > 4  || y > 4){
            if(x - y >= 2){
                // A win, return -1
                return -1;
            }
            if(y- x >= 2){
               return 1;
            }
        }
        return 0;
    }

    // End this round and begin next round
    public int nextRound(int x, int y){
        if(isNextRound(x, y) == -1){
            // x win in this round
            teamAScoreL++;
            teamAL.setText(Integer.toString(teamAScoreL));
            teamAScoreS = teamBScoreS = 0;
            teamAS.setText(Integer.toString(teamAScoreS));
            teamBS.setText(Integer.toString(teamBScoreS));
            return 1;
        } else if(isNextRound(x, y) == 1){
            // y win in this round
            teamBScoreL++;
            teamBL.setText(Integer.toString(teamBScoreL));
            teamAScoreS = teamBScoreS = 0;
            teamAS.setText(Integer.toString(teamAScoreS));
            teamBS.setText(Integer.toString(teamBScoreS));
            return -1;
        }
        return 0;
    }

    public void isEnd(int x, int y){
        if(isNextRound(x, y) == -1){
            // x win
            Toast.makeText(getApplicationContext(), "Game over, A win!!!", Toast.LENGTH_LONG).show();
        }
        if(isNextRound(x, y) == 1){
            Toast.makeText(getApplicationContext(), "Game over, B win!!!", Toast.LENGTH_LONG).show();
        }
    }

}