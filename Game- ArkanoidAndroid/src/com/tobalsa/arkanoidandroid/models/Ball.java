package com.tobalsa.arkanoidandroid.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.tobalsa.arkanoidandroid.painting.GameView;

public class Ball {
	
	private Bitmap bitmap;
	private GameView gameView;

	private float x, y, vx, vy;
	private boolean touched;
	private Rect rect;

	public static final int GRADOS45 = 1;
	public static final int GRADOS90 = 2;
	public static final int GRADOS135 = 3;
	public static final int GRADOS180 = 4;

	private int direccion;

	public Ball(Bitmap bitmap, GameView gameView, float x, float y) {
		this.bitmap = bitmap;
		this.gameView = gameView;
		this.x = x;
		this.y = y;
		this.vx = 9;
		this.vy = 8;
		rect = new Rect((int)x, (int)y, (int)(x+bitmap.getWidth()), (int)(y+bitmap.getHeight()));
		direccion = 1;
	}

	public Bitmap getBitmap() {return bitmap;}
	public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}
	public GameView getGameView() {return gameView;}
	public void setGameView(GameView gameView) {this.gameView = gameView;}

	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	public float getY() {return y;}
	public void setY(float y) {	this.y = y;}
	public boolean isTouched() {return touched;}
	public void setTouched(boolean touched) {this.touched = touched;}
	public Rect getRect() {return rect;}
	public void setRect(Rect rect) {this.rect = rect;}
	public float getVx() {return vx;}
	public void setVx(float vx) {this.vx = vx;}
	public float getVy() {return vy;}
	public void setVy(float vy) {this.vy = vy;}

	public void draw(Canvas canvas) {
		//update();
		canvas.drawBitmap(bitmap, getX(), getY(), null);
	}


	public void move(float vx, float vy) {
		vx = this.vx;
		vy = this.vy;
		switch(direccion) {
		case GRADOS45:
			setX(getX() + vx);
			setY(getY() - vy);
			break;
		case GRADOS90:
			setX(getX() - vx);
			setY(getY() - vy);
			break;
		case GRADOS135:
			setX(getX() - vx);
			setY(getY() + vy);
			break;
		case GRADOS180:
			setX(getX() + vx);
			setY(getY() + vy);
			break;
		}
	}
	
	public void update() {
//		Choque con la derecha
		if (getX() >= gameView.getWidth() - bitmap.getWidth()) {
			vx = -vx;
			x = gameView.getWidth() - bitmap.getWidth();
			Log.d("derecha", ""+vx);
		}
//		Choque con el techo
		if (y <= 0) {
			vy = -vy;
			y = 0;
			Log.d("techo", ""+vy);
		}
//		Choque Izquierda
		if (x <= 0) {
			vx = -vx;
			x = 0;
			Log.d("izq", ""+vx);
		}
//		Choque con el suelo
		if (y >= gameView.getHeight() - bitmap.getHeight()) {
			//Bola destruida
			
			//GameView.getThread().setRunning(false);
			
			vy = -vy;
			y = gameView.getHeight() - bitmap.getHeight();
			Log.d("suelo", ""+vy);
			
		}
	}

}
