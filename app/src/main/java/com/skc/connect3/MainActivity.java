package com.skc.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: Zero, 1: Cross, 2: None
    int activePlayer = 0;
    boolean active = true;
    int winner = 2, flag = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningStates = {{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView current = (ImageView) view;
        TextView update = findViewById(R.id.textView2);

        int counter = Integer.parseInt(current.getTag().toString());

        if(gameState[counter] == 2 && active) {
            gameState[counter] = activePlayer;
            current.setTranslationY(-1500);

            if (activePlayer == 0) {
                current.setImageResource(R.drawable.zero);
                activePlayer = 1;
            } else {
                current.setImageResource(R.drawable.cross);
                activePlayer = 0;
            }
            current.animate().rotation(360).translationYBy(1500).setDuration(1000);
            update.setText("Player " + (activePlayer + 1) + "'s Turn");

//            int winningCombination = -1;

            for (int[] winningState : winningStates) {
//                winningCombination++;
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {
                    if (activePlayer == 0) {
                        winner = 2;
                    } else {
                        winner = 1;
                    }
                    flag = 1;
                    break;
                }
            }

            boolean draw = true;

            for (int x : gameState) {
                if(x == 2) {
                    draw = false;
                    break;
                }
            }

            if(draw && flag == 0) {
                update.setText("It's a Draw!");

//                GridLayout gridLayout = findViewById(R.id.gridLayout);
//
//                for (int i = 0; i < gridLayout.getChildCount(); i++) {
//                    ImageView currentView = (ImageView) gridLayout.getChildAt(i);
//                    currentView.animate().rotationX(360).setDuration(1000);
//                }

                Button playAgainButton = findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);
                active = false;
            }

            if (flag == 1) {
                active = false;
                update.setText("Hurray! Player " + winner + " has won");

//                GridLayout gridLayout = findViewById(R.id.gridLayout);
//
//                for (int i: winningStates[winningCombination]) {
//                    ImageView currentView = (ImageView) gridLayout.getChildAt(i);
//                    currentView.animate().rotationX(360).setDuration(1000);
//                }

                Button playAgainButton = findViewById(R.id.playAgainButton);
                playAgainButton.setVisibility(View.VISIBLE);
                flag = 0;
            }
        }
    }

    public void playAgain (View view){
        Button playAgainButton = (Button) view;
        playAgainButton.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView current = (ImageView) gridLayout.getChildAt(i);
            current.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        activePlayer = 0;
        active = true;
        TextView update = findViewById(R.id.textView2);
        update.setText("Player " + (activePlayer + 1) + "'s Turn");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}