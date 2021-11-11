package com.game.game.game0;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class GE implements GELite {

    protected int x, y;
    protected int w, h;

    protected Paint paint = new Paint();

    protected boolean drawable = true;
    protected boolean clickable = true;

    public abstract void init();

    private Scena scena;

    public GE(Scena _scena) {
        scena = _scena;
        init();
    }

    @Override
    public void remove(GE ge) {
        scena.remove(this);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(boolean drawable) {
        this.drawable = drawable;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public Scena getScena() {
        return scena;
    }

    public void setScena(Scena scena) {
        this.scena = scena;
    }

    public android.graphics.Rect getCollissionRect() {
        return new android.graphics.Rect(x, y, x + w, y + h);
    }
}
