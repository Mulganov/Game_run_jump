package com.game.game.game0;

import android.graphics.Canvas;

public interface GELite {
    void onDraw(Canvas canvas, long delta);

    void onClick(int x, int y);

    void update(long delta);

    void remove(GE ge);
}
