package com.game.game.game0;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class CustomView extends View {
    private Scena scena = new Scena() {
        @Override
        public void init() {

        }
    };

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        scena.onDraw(canvas, Game.getInstance().getDelta());

    }


    public void draw(){
        post(() -> invalidate());
    }

    public Scena getScena() {
        return scena;
    }

    public void setScena(Scena scena) {
        this.scena = scena;
    }

    public void start() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                draw();
                handler.postDelayed(this::run, 10);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getActionMasked() == MotionEvent.ACTION_DOWN){
            if (Game.getInstance().newGame){
                setScena(Scenas.getScena());
                Game.getInstance().newGame = false;

                Activity activity = Game.getInstance().activity;

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView text = activity.findViewById(R.id.textView);
                        activity.findViewById(R.id.textView2).setVisibility(View.INVISIBLE);
                        text.setText("You Lose");
                        text.setVisibility(View.INVISIBLE);
                    }
                });
            }else
                scena.onClick((int)event.getX(), (int)event.getY());
        }

        return true;
    }
}
