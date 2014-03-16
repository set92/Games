package com.tobalsa.starfighter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

//Clase que crea el motor de juegos, el cual tiene los metodos que se usaran en todos los juegos
public class SFEngine {
	
	//Constantes
	public static final int GAME_THREAD_DELAY = 2000; // Retraso con el que empezara el hilo del juego
	public static final int MENU_BUTTON_ALPHA = 0; // Opacidad de los botones de menu
	public static final boolean HAPTIC_BUTTON_FEEDBACK = true; // Al pulsar el boton que el usuario tenga una respuesta tactil
	public static final int SPLASH_SCREEN_MUSIC = R.raw.background_music;
	public static final int R_VOLUME = 100;
	public static final int L_VOLUME = 100;
	public static final boolean LOOP_BACKGROUND_MUSIC = true;
	public static final int GAME_THREAD_FPS_SLEEP = (1000/60);//El juego correra a 60FPS
	public static final int BACKGROUND_LAYER_ONE = R.drawable.bgstars;
	public static final int BACKGROUND_LAYER_TWO = R.drawable.debris;
	public static final int PLAYER_SHIP = R.drawable.sprite_sheet;
	public static final int PLAYER_BANK_LEFT_1 = 1;
	public static final int PLAYER_RELEASE = 3;
	public static final int PLAYER_BANK_RIGHT_1 = 4;
	public static final int PLAYER_FRAMES_BETWEEN_ANI = 9;//Habra un frame de la nave por cada 9 frames del juego
	public static final float PLAYER_BANK_SPEED = .1f;//Velocidad de la nave
	
	
	public static float SCROLL_BACKGROUND_1 = .002f;
	public static float SCROLL_BACKGROUND_2 = .007f;
	public static int playerFlightAction = 0;//Para saber que accion esta realizando
	public static float playerBankPosX = 1.75f;//Posicion de la nave en el eje X
	
	public static Context context;
	public static Thread musicThread;
	
	
	/*Mata los hilos sale del juego*/
	public boolean onExit(View v){
		try {
			Intent bgMusic = new Intent(context, SFMusic.class);
			context.stopService(bgMusic);
			musicThread.interrupt();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
