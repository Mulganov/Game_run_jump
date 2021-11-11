package com.game.game.game0;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Scenas {

    public static Scena getScena(){
        int ww = Game.getInstance().w;
        int hh = Game.getInstance().h;

        Scena scena = new Scena() {
            @Override
            public void init() {
                add(new Background(this) {
                    @Override
                    public void init() {
                        nrect = ww / 10;
                        indexMax = 3;
                    }
                })
                .add(new Player(this) {
                    @Override
                    public void init() {
                        h = (int) (hh / Game.map.length * 0.7f);
                        w = h;
                        x = 0;
                        y = (hh - h) / 2;

                        paint.setColor(Color.GRAY);
                    }
                })
                .add(new Blocks(this) {
                    @Override
                    public void init() {
                        nrect = 2;
                        indexMax = 10;
                    }
                })
                ;
            }
        };

        return scena;
    }
}
