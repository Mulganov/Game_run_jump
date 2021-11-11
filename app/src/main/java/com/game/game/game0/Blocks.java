package com.game.game.game0;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

public abstract class Blocks extends GE {

    ArrayList<Platform> platforms = new ArrayList<>();

    private int n = 10, m = 1000;

    private float speed = 0.5f;

    public int nrect;

    public Blocks(Scena _scena) {
        super(_scena);

        int ww = Game.getInstance().w;
        int hh = Game.getInstance().h;

        int[][] a = randomMap();

        int size = hh / a.length;

        for (int i = 0; i < a.length; i++){
            for (int j = 0; j < a[i].length; j++){
                int finalJ = j;
                int finalI = i;
                if (a[finalI][finalJ] == 1){
                    Platform p = new Platform(getScena()) {
                        @Override
                        public void init() {
                            w = size;
                            h = size;

                            x = finalJ * size;
                            y = finalI * size;

                            paint.setColor(Color.BLACK);
                        }
                    };
                    platforms.add(p);
                }

                if (a[finalI][finalJ] == 2){
                    Platform p = new Platform(getScena()) {
                        @Override
                        public void init() {
                            w = size;
                            h = size;

                            x = finalJ * size;
                            y = finalI * size;

                            paint.setColor(Color.GREEN);
                        }
                    };
                    platforms.add(p);
                }

            }
        }

    }

    private int[][] randomMap() {
        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++){
            for (int j = 10; j < m-1; j++){
                a[i][j] = (new Random().nextInt(10) > 8 ? 1:0);
            }
        }

        for (int i = 0; i < n; i++){
            a[i][m-1] = 2;
        }

        for (int i = 0; i < 10; i++){
            a[0][i] = 1;
            a[a.length-1][i] = 1;
        }

        return a;
    }

    @Override
    public void onDraw(Canvas canvas, long delta) {
        for (int i = 0; i < platforms.size(); i++){
            platforms.get(i).onDraw(canvas, delta);
        }
    }

    @Override
    public void onClick(int x, int y) {
    }

    public int indexMax;
    private int index = 0;

    @Override
    public void update(long delta) {

        for(int i = 0; i < platforms.size(); i++){
            Platform rect = platforms.get(i);
            rect.x -= delta * speed;

            rect.lastGoX = (int) (delta * speed);

            if ( rect.x + rect.w < 0 ){
                platforms.remove(rect);
            }
        }

        if (index == indexMax){
            index = 0;
            if (platforms.size() < nrect){
                Platform rect = new Platform(getScena()) {
                    @Override
                    public void init() {
                        int ww = Game.getInstance().w;
                        int hh = Game.getInstance().h;

                        w = new Random().nextInt(ww / 30) + ww / 20;
                        h = new Random().nextInt(hh / 30) + hh / 20;

                        paint.setARGB(255, 0, 0, 0);

                        y = new Random().nextInt(hh - h);

                        x = ww;
                    }
                };

                platforms.add(rect);
            }
        }


        index++;
    }
}
