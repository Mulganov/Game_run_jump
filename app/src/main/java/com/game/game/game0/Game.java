package com.game.game.game0;

import android.util.Log;

public class Game {

    public static int[][] map = new int[][]{
            new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    private static Game game = new Game();

    public int w, h;
    public boolean newGame;

    public static Game getInstance() {
        return game;
    }

    private Game(){
    }

    private CustomView view;
    public MainActivity activity;

    private long currentTime;

    public void init(CustomView _view, MainActivity mainActivity){
        view = _view;

        w = view.getWidth();
        h = view.getHeight();

        currentTime = System.currentTimeMillis();

        Scena scena = Scenas.getScena();

        view.setScena(scena);

        view.start();
    }

    public long getDelta(){
        long delta = System.currentTimeMillis();
        delta -= currentTime;

        currentTime = System.currentTimeMillis();

        return delta;
    }

}
