package com.game.game.game0;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public abstract class Player extends Platform {

    private int vectorX = 1;
    private int oldVector = 1;

    private final float speedFinal = 0.1f;
    private float speed = speedFinal;

    private float[][] arrowUp = new float[][]{
            new float[]{0,0,0,0,0,1,0,0,0,0,0},
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{0,0,0,1,1,1,1,1,0,0,0},
            new float[]{0,0,1,1,1,1,1,1,1,0,0},
            new float[]{0,1,1,1,1,1,1,1,1,1,0},
            new float[]{1,1,1,1,1,1,1,1,1,1,1},
            new float[]{1,1,0,0,1,1,1,0,0,1,1},
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{0,0,0,0,1,1,1,0,0,0,0}
    };

    private float[][] arrowDown = new float[][]{
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{1,1,0,0,1,1,1,0,0,1,1},
            new float[]{1,1,1,1,1,1,1,1,1,1,1},
            new float[]{0,1,1,1,1,1,1,1,1,1,0},
            new float[]{0,0,1,1,1,1,1,1,1,0,0},
            new float[]{0,0,0,1,1,1,1,1,0,0,0},
            new float[]{0,0,0,0,1,1,1,0,0,0,0},
            new float[]{0,0,0,0,0,1,0,0,0,0,0},
    };

    ArrayList<Rect> arrow = new ArrayList<>();

    public Player(Scena _scena) {
        super(_scena);
        gravity = true;
    }

    private void setArrow(float[][] list) {
        arrow = new ArrayList<>();

        int size = ((int)w / list.length);

        for (int i = 0; i < list.length; i++){
            for (int j = 0; j < list[i].length; j++){
                int finalJ = j;
                int finalI = i;

                int finalSize = size;
                int finalI1 = i;
                int finalJ1 = j;
                Rect rect = new Rect(getScena()) {
                    @Override
                    public void init() {
                        w = finalSize;
                        h = finalSize;

                        x = Player.this.x + finalJ * finalSize;
                        y = Player.this.y + finalI * finalSize;
                        if (list[finalI1][finalJ1] == 1){
                            paint.setColor(Color.BLACK);
                        }else{
                            paint.set(Player.this.paint);
                        }
                    }
                };
                arrow.add(rect);
            }
        }
    }


    @Override
    public void onDraw(Canvas canvas, long delta) {
        super.onDraw(canvas, delta);

        for (int i = 0; i < arrow.size(); i++){
            arrow.get(i).onDraw(canvas, delta);
        }
    }

    @Override
    public void update(long delta) {
        super.update(delta);

        int xx = (int) (delta * vectorX * speed);

        x += xx;

        lastGoX = xx;

        getScena().post(new GERun() {
            @Override
            public void run(GE ge) {
                if ( !(ge instanceof Blocks) ) return;

                if (ge == Player.this) return;

                Blocks b = (Blocks) ge;

                for (int i = 0; i < b.platforms.size(); i++){

                    Platform p = b.platforms.get(i);

                    if (!p.body) continue;

                    switch (p.isCollision(Player.this)){
                        case 0:
                        case 1:
                            y -= lastGoY;

                            if (vector == 1){
                                vector = -1;
                            }else{
                                vector = 1;
                            }

                            speed += speedFinal;

//                            gravity = false;

                            if (p.paint.getColor() == Color.GREEN){
                                MainActivity activity = Game.getInstance().activity;

                                Game.getInstance().newGame = true;

                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView text = activity.findViewById(R.id.textView);
                                        activity.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                                        text.setText("You Win");
                                        text.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            break;
                        case 2:
                            x = p.x - w;

                            speed = speedFinal;

                            if (p.paint.getColor() == Color.GREEN){
                                MainActivity activity = Game.getInstance().activity;

                                Game.getInstance().newGame = true;

                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView text = activity.findViewById(R.id.textView);
                                        activity.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                                        text.setText("You Win");
                                        text.setVisibility(View.VISIBLE);
                                    }
                                });
                            }
                            break;
                        case 3:
                            break;
                        default:
                            break;

                    }
                }

            }
        });

        if (x >= Game.getInstance().w / 2){
            x = Game.getInstance().w / 2;
        }

        upDateArrow();
    }

    private void upDateArrow() {

        switch (vector){
            case 1:
                setArrow(arrowDown);
                break;
            case -1:
                setArrow(arrowUp);
                break;
            case 0:
                setArrow( oldVector == 1 ? arrowDown:arrowUp );
        }
    }

    @Override
    public void onClick(int x, int y) {
        if (vector == 1){
            vector = -1;
            oldVector = 1;
        }else{
            vector = 1;
            oldVector = -1;
        }

        gravity = true;
    }

    @Override
    public void remove(GE ge) {
        super.remove(ge);

        MainActivity activity = Game.getInstance().activity;

        Game.getInstance().newGame = true;

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView text = activity.findViewById(R.id.textView);
                activity.findViewById(R.id.textView2).setVisibility(View.VISIBLE);
                text.setText("You Lose");
                text.setVisibility(View.VISIBLE);
            }
        });
    }
}
