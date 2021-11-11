package com.game.game.game0;

import android.graphics.Canvas;
import android.graphics.RectF;

public abstract class Rect extends GE {

    public Rect(Scena _scena) {
        super(_scena);
    }

    @Override
    public void onDraw(Canvas canvas, long delta) {
//        for (int i = y; i < h + y; i++){
//            for (int j = x; j < w + x; j++){
//                canvas.drawPoint(j, i, paint);
//            }
//        }
        RectF rectF = new RectF();
        rectF.top = y;
        rectF.bottom = y + h;
        rectF.left = x;
        rectF.right = x + w;

        canvas.drawRect(rectF, paint);
    }

    @Override
    public void onClick(int x, int y) {
    }

    @Override
    public void update(long delta) {
    }
}
