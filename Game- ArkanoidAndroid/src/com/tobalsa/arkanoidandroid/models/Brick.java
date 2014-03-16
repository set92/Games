package com.tobalsa.arkanoidandroid.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;

import com.tobalsa.arkanoidandroid.painting.GameView;

public class Brick {
	
	private Bitmap bitmap;
	private GameView gameView;
	private float x, y;
	private Rect rect;

	
	public Brick(Bitmap bitmap, GameView gameView, float x, float y) {
		super();
		this.bitmap = bitmap;
		this.gameView = gameView;
		this.x = x;
		this.y = y;
		rect = new Rect((int)x, (int)y, (int)(x+bitmap.getWidth()), (int)(y+bitmap.getHeight()));

	}

	public Bitmap getBitmap() {	return bitmap;}
	public void setBitmap(Bitmap bitmap) {this.bitmap = bitmap;}
	public GameView getGameView() {return gameView;}
	public void setGameView(GameView gameView) {this.gameView = gameView;}
	public float getX() {return x;}
	public void setX(float x) {this.x = x;}
	public float getY() {return y;}
	public void setY(float y) {this.y = y;}
	public Rect getRect() {return rect;}
	public void setRect(Rect rect) {this.rect = rect;}
	
	public void draw(Canvas canvas) {
		//update();
		canvas.drawBitmap(bitmap, getX(), getY(), null);
		
	}
	
	public void update(){
		
	}
	
	
}
