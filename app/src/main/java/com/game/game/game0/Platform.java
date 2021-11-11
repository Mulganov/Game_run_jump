package com.game.game.game0;


import android.util.Log;

public abstract class Platform extends Rect {

    protected boolean gravity = false;

    protected boolean body = true;

    protected int vector = 1;

    protected int lastGoY;
    protected int lastGoX;

    protected final float kYdown = 0.5f;

    public Platform(Scena _scena){
        super(_scena);
    }

    @Override
    public void update(long delta) {
        if (gravity){
            y += delta * kYdown * vector;

            lastGoY = (int) (delta * kYdown * vector);

            if (y > Game.getInstance().h){
                remove(this);
            }

            if (y < -h){
                remove(this);
            }

            if (x + w < 0){
                remove(this);
            }

            if (x > Game.getInstance().w){
                remove(this);
            }
        }
    }

    public int isCollision(GE ge){
        int n = -1;

        if (android.graphics.Rect.intersects(ge.getCollissionRect(), getCollissionRect())){
            if ( (ge.x + ge.w > x && Math.abs(ge.x + ge.w - x) < lastGoX * 1.5f) &&
                    (
                                    ge.y < y && ge.y + ge.h > y + h ||
                                    y < ge.y && ge.y < y + h ||
                                    y < ge.y + ge.h && ge.y + ge.h < y + h ||
                                    ge.y > y && ge.y + ge.h < y + h
                    )
            ){
                n = 2;
            }else{
                n = 1;
            }

        }



//        if ( x <= ge.x && ge.x <= x + w ){
//            if ( y - ge.h <= ge.y  && y + h >= ge.y){
//
//                if ( y - ge.h <= ge.y ){
//                    n = 0; // pl down
//                }
//
//                if ( y + h >= ge.y ){
//                    n = 1; // pl up
//                }
//
//                Log.i("qwer", n + "");
////                if ( y + w < ge.y ){
////                    n = 0; // pl down
////                }
////
////                if ( ge.y + ge.w < y ){
////                    n = 0; // pl up
////                }
//            }
//        }

        return n;
    }



    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }

    public boolean getGravity(){
        return gravity;
    }
}
