package com.tobalsa.juegoevitarobstaculos.models;

public class Velocidad {

	public static final float DIRECCION_DERECHA = -1;
	public static final float DIRECCION_IZQUIERDA = 1;
	public static final float DIRECCION_ARRIBA = 1;
	public static final float DIRECCION_ABAJO = -1;
	
	private float xv = 1; //Velocidad que lleva en el eje X
	private float yv = 1; //Velocidad que lleva en el eje Y
	
	private float direccionEnX = DIRECCION_DERECHA;
	private float direccionEnY = DIRECCION_ABAJO;
	
	public Velocidad() {
		this.xv = 0; //Velocidades iniciales
		this.yv = 0;
	}
	public Velocidad(float xv, float yv){
		this.xv = xv;
		this.yv = yv;
	}
	public float getxv() {
		return xv;
	}
	public void setxv(float xv) {
		this.xv = xv;
	}
	public float getyv() {
		return yv;
	}
	public void setyv(float yv) {
		this.yv = yv;
	}
	public float getDireccionEnX() {
		return direccionEnX;
	}
	public void setDireccionEnX(float direccionEnX) {
		this.direccionEnX = direccionEnX;
	}
	public float getDireccionEnY() {
		return direccionEnY;
	}
	public void setDireccionEnY(float direccionEnY) {
		this.direccionEnY = direccionEnY;
	}
	
	public void cambiarDireccionEnX() {
		direccionEnX = direccionEnX * -1;
	}
	
	public void cambiarDireccionEnY() {
		direccionEnY = direccionEnY * -1;
	}
}
