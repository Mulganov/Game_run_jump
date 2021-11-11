package com.game.game.game0;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

public abstract class Scena implements GELite {
    private ArrayList<GE> list = new ArrayList<>();

    public abstract void init();

    public Scena(){
        init();
    }

    public Scena add(GE ge){
        list.add(ge);
        return this;
    }

    public GE get(int index){
        try {
            return list.get(index);
        }catch (Exception ex){
            return null;
        }
    }

    @Override
    public void onDraw(Canvas canvas, long delta) {
        Log.i("delta", delta  + "");

        int index = 0;
        for ( ; true; ){
            GE ge = get(index);

            if (ge == null) break;

            ge.onDraw(canvas, delta);

            ge.update(delta);

            index++;
        }
    }

    @Override
    public void update(long delta) {
    }

    @Override
    public void onClick(int x, int y) {
        post(new GERun() {
            @Override
            public void run(GE ge) {
                if (ge.clickable)
                    ge.onClick(x, y);
            }
        });
    }

    @Override
    public void remove(GE ge) {
        list.remove(ge);
    }

    public void post(GERun geRun) {
        int index = 0;
        for ( ; true; ){
            GE ge = get(index);

            if (ge == null) break;

            geRun.run(ge);
            index++;
        }
    }
}
