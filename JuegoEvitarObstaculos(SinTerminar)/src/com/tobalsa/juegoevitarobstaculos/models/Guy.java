package com.tobalsa.juegoevitarobstaculos.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Guy {
	
	private Bitmap bitmap;
//	private static final int ROWS = 4;
//	private static final int COLUMNS = 3;
//	private int width;
//	private int height;
	private float x, y;
	private Velocidad velocidad;

	public Guy(Bitmap bitmap, float x, float y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
//		this.width = bitmap.getWidth() / COLUMNS;
//		this.height = bitmap.getHeight() / ROWS;
		this.velocidad = new Velocidad();
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}

	public Velocidad getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(Velocidad velocidad) {
		this.velocidad = velocidad;
	}

	public void draw(Canvas canvas) {
		//		int srcX =  width;
		//        int srcY = 1 * height;
		//        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
		//        Rect dst = new Rect((int)x, (int)y,(int) x + width,(int) y + height);
		//        canvas.drawBitmap(bitmap, src, dst, null);
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}

	public void update() {
		x += (velocidad.getxv() * velocidad.getDireccionEnX()); 
		y += (velocidad.getyv() * velocidad.getDireccionEnY());
	}

}
