/**
 *
 * @author Courtney Diotte
 *
 * @version 1.0
 * *
 * @section DESCRIPTION
 * Rock Paper Scissors App - Main Activity
 *
 * @section LICENSE
 * *
 * Copyright 2018
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * @section Academic Integrity
 * I certify that this work is solely my own and complies with
 * NBCC Academic Integrity Policy (policy 1111)
 */

package android.example.com.rockpaperscissors;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RPS rps = new RPS();
    private ImageView playerMove;
    private ImageView computerMove;
    private TextView outcome;

    /**
     * store textview and images in private members
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerMove = findViewById(R.id.player_move);
        computerMove = findViewById(R.id.game_move);
        outcome = findViewById(R.id.outcome_text);

    }

    /**
     * onClick event to play RPS game
     * @param view
     */
    public void play(View view) {

        //Player's move
        //determine player's choice and set image accordingly
        String playersChoice = view.getTag().toString();

        switch(playersChoice)
        {
            case "paper":
                rps.playerMakesMove(RPS.PAPER);
                playerMove.setImageResource(R.drawable.paper);
                break;
            case "rock":
                rps.playerMakesMove(RPS.ROCK);
                playerMove.setImageResource(R.drawable.rock);
                break;
            case "scissors":
                rps.playerMakesMove(RPS.SCISSOR);
                playerMove.setImageResource(R.drawable.scissors);
                break;
            default:
                break;
        }

        //Game's move
        //sets image according to which random number was generated
        switch(rps.getGameMove()){
            case 0:
                computerMove.setImageResource(R.drawable.rock);
                break;
            case 1:
                computerMove.setImageResource(R.drawable.paper);
                break;
            case 2:
                computerMove.setImageResource(R.drawable.scissors);
                break;
            default:
                break;

        }

        //set the outcome text to either WIN, LOSE or DRAW
        outcome.setText(rps.winLoseOrDraw());

        //animate the images
        animateImages(playerMove, computerMove);
    }

    /**
     * Animate player and game choices
     * @param player
     * @param computer
     */
    private void animateImages(ImageView player, ImageView computer) {
        // you can animate any prop that there is a setter for
        // player.setRotationX(0f);

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(computer,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }
}
