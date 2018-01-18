package com.example.nestorfernandez.balllaberynth;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by nestor.fernandez on 18/01/2018.
 */

public class GameView extends SurfaceView {
    private SurfaceHolder holder;
    Bola bola;

    public GameView(Context context) {
        super(context);
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback(){

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                bola = new Bola(getContext());
                Canvas c = holder.lockCanvas();
                draw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        bola.Draw(canvas);
    }

    public void comunicateData(float[] data) {
        bola.comunicateData(data);
        Canvas c = holder.lockCanvas();
        draw(c);
        holder.unlockCanvasAndPost(c);
    }
}
