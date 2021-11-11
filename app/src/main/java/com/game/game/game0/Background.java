package com.game.game.game0;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public abstract class Background extends GE {

    ArrayList<Rect> rects = new ArrayList<>();

    public int nrect;

    public Background(Scena _scena) {
        super(_scena);
    }

    @Override
    public void onDraw(Canvas canvas, long delta) {
        for (int i = 0; i < rects.size(); i++){
            rects.get(i).onDraw(canvas, delta);
        }
    }

    @Override
    public void onClick(int x, int y) {
    }

    public int indexMax;
    private int index = 0;

    @Override
    public void update(long delta) {

        for(int i = 0; i < rects.size(); i++){
            Rect rect = rects.get(i);
            rect.y += delta * 0.7;

            if ( rect.y > Game.getInstance().h ){
                rects.remove(rect);
            }
        }

        if (index == indexMax){
            index = 0;
            if (rects.size() < nrect){
                Rect rect = new Platform(getScena()) {
                    @Override
                    public void init() {
                        int ww = Game.getInstance().w;
                        int hh = Game.getInstance().h;

                        w = new Random().nextInt(ww / 30) + ww / 100;
                        h = new Random().nextInt(hh / 30) + hh / 100;

                        paint.setARGB(40, 0, 0, 0);

                        y = 0;

                        x = new Random().nextInt(ww);
                    }
                };

                rects.add(rect);
            }
        }


        index++;
    }
}
