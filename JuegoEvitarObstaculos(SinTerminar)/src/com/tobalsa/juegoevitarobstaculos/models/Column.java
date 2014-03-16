package com.tobalsa.juegoevitarobstaculos.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Column {
	
	private Bitmap bitmap;
	private float x, y;
	private Velocidad velocidad;

	public Column(Bitmap bitmap, float x, float y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
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
		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
		canvas.drawBitmap(bitmap, 720, 1280, null);
	}
	
	public void update() {
			x += (velocidad.getxv() * velocidad.getDireccionEnX()); 
			y += (velocidad.getyv() * velocidad.getDireccionEnY());
	}
	
}
