package com.example.nestorfernandez.balllaberynth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

/**
 * Created by nestor.fernandez on 18/01/2018.
 */

public class Bola {
    int x, y;
    int xSpeed, ySpeed;
    static final int minSpeed=-10;
    static final int maxSpeed=10;
    Bitmap bola;
    Context ctx;
    int width, heigth;

    public Bola(Context ctx) {
        this.ctx = ctx;
        bola = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.twitter32);
        x=y=100;
    }

    public void Draw(Canvas canvas){
        canvas.drawBitmap(bola,x,y,null);
    }

    public void comunicateData(float[] data) {
        int newx=x;
        int newy=y;

        //Cogemos el alto y ancho de la pantalla y los guardamos en heigth y width
        Point dsize=new Point();
        Display d=((WindowManager)ctx.getSystemService(ctx.WINDOW_SERVICE)).getDefaultDisplay();
        d.getSize(dsize);
        heigth=dsize.x-bola.getHeight();
        width=dsize.y-bola.getWidth();

        //Nuevas velocidades
        if(d.getRotation()== Surface.ROTATION_90 || d.getRotation()==Surface.ROTATION_270){
            //ejes rotados, los datos se leen al reves
            xSpeed+=-data[1];
            ySpeed+=data[0];
        }else{
            xSpeed+=-data[0];
            ySpeed+=data[1];
        }

        //limitamos la velocidad

        //Operador ternario. Si xSpeed es mayor que maxSpeed, xSpeed pasa a valer maxSpeed. Si no, pasa a valer xSpeed
        xSpeed=xSpeed>maxSpeed?maxSpeed:xSpeed;
        xSpeed=xSpeed<minSpeed?minSpeed:xSpeed;

        ySpeed=ySpeed>maxSpeed?maxSpeed:ySpeed;
        ySpeed=ySpeed<minSpeed?minSpeed:ySpeed;

        //actualizamos la posicion
        newx+=xSpeed;
        newy+=ySpeed;

        //Comprobamos limites de la pantalla
        if(newx>heigth){
            xSpeed=-xSpeed; //Rebota
            x=heigth;
        }else if(newx<0){
            xSpeed=-xSpeed;
            x=0;
        }else{
            x=newx;
        }

        if(newy>width){
            ySpeed=-ySpeed; //Rebota
            y=width;
        }else if(newy<0){
            ySpeed=-ySpeed;
            y=0;
        }else{
            y=newy;
        }
    }
}
