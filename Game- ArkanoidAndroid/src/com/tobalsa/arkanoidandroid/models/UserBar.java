package com.tobalsa.arkanoidandroid.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.tobalsa.arkanoidandroid.painting.GameView;

public class UserBar {

	private Bitmap bitmap;
	private GameView gameView;
	private float x, y, width, height;
	private boolean touched;
	private Rect rect;
	

	public UserBar(Bitmap bitmap, GameView gameView, float x, float y, float width, float height) {
		this.bitmap = bitmap;
		this.gameView=gameView;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rect((int)x, (int)y, (int)(x+width), (int)(y+height));
	}

	public Bitmap getBitmap() {return bitmap;}
	public float getX() {return x;}
	public float getY() {return y;}
	public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}
	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}
	public boolean isTouched() {return touched;}
	public void setTouched(boolean touched) {this.touched = touched;}
	public GameView getGameView() {return gameView;}
	public void setGameView(GameView gameView) {this.gameView = gameView;}
	public float getWidth() {return width;}
	public void setWidth(float width) {this.width = width;}
	public float getHeight() {return height;}
	public void setHeight(float height) {this.height = height;}
	public Rect getRect() {return rect;}
	public void setRect(Rect rect) {this.rect = rect;}

	public void draw(Canvas canvas) {
		//update();
		canvas.drawBitmap(bitmap, getX(), getY(), null);
	}

	public void update() {
//		Choque derecha
		if (x > gameView.getWidth() - bitmap.getWidth()) {
			x = gameView.getWidth() - bitmap.getWidth();
		}
//		Choque izquierda
		if (x <= 0) {
			x = 0;
		}
	}
}
