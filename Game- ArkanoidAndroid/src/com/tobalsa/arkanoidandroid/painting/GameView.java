package com.tobalsa.arkanoidandroid.painting;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tobalsa.arkanoidandroid.BallThread;
import com.tobalsa.arkanoidandroid.R;
import com.tobalsa.arkanoidandroid.models.Ball;
import com.tobalsa.arkanoidandroid.models.Brick;
import com.tobalsa.arkanoidandroid.models.UserBar;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG= "ARKANOID_ANDROID"; 

	private static GameLoop thread;
	private BallThread ballThread;

	private UserBar barraJugador;
	private Ball bola;
	private Brick ladrillo;
	private List<Brick> listaLadrillos = new ArrayList<Brick>();

	public UserBar getBarraJugador() {return barraJugador;}
	public void setBarraJugador(UserBar barraJugador) {this.barraJugador = barraJugador;}
	public Ball getBola() {return bola;}
	public void setBola(Ball bola) {this.bola = bola;}
	public static GameLoop getThread() {return thread;}
	public void setThread(GameLoop thread) {GameView.thread = thread;}

	public GameView(Context context) {
		super(context);
		getHolder().addCallback(this);

		// Instanciamos objetos que no necesiten medidas
		//barraJugador = new UserBar(BitmapFactory.decodeResource(getResources(), R.drawable.user_bar), this);

		thread = new GameLoop(getHolder(), this);

		setFocusable(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// Instanciamos objetos que SI necesiten medidas
		barraJugador = new UserBar(BitmapFactory.decodeResource(getResources(), R.drawable.user_bar), this, getWidth()/2 - 125/2, getHeight()*90/100, 125, 50);
		bola = new Ball(BitmapFactory.decodeResource(getResources(), R.drawable.ball), this, getWidth()/2, getHeight()/2);
		ladrillo = new Brick(BitmapFactory.decodeResource(getResources(), R.drawable.brick), this, 300, 300);
		
		ballThread = new BallThread(bola);

		thread.setRunning(true);
		thread.start();
		ballThread.setRunning(true);
		ballThread.start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
		//Una vez creada la vista se puede definir la posicion de la barra
		//barraJugador.setY(getHeight()*90/100);
		//barraJugador.setX(getWidth()/2 - 200);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Destruyendo vista");
		boolean retry = true;

		thread.setRunning(false);
		ballThread.setRunning(false);

		while (retry) {
			try {
				thread.join();
				ballThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// Se vuelve a intentar destruir la vista
			}
		}
		Log.d(TAG, "El juego se detuvo correctamente");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (!(y <= getHeight()/2)) {
				barraJugador.setX(x - barraJugador.getWidth()/2);
			}
			
			// si se toca la parte de abajo se cierra el juego
//			if (event.getY() > getHeight() - 50) {
//				thread.setRunning(false);
//				((Activity)getContext()).finish();
//			} else {
//				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
//			}
		} if (event.getAction() == MotionEvent.ACTION_MOVE) {
			//barraJugador.setX(x - barraJugador.getWidth()/2);
			if (!(y <= getHeight()/2)) {
				barraJugador.setX(x - barraJugador.getWidth()/2);
			}
			
			if (barraJugador.isTouched()) {
				// Desplazamos la barra
				barraJugador.setX((int)event.getX());
			}
		} if (event.getAction() == MotionEvent.ACTION_UP) {
			if (barraJugador.isTouched()) {
				barraJugador.setTouched(false);
			}
		}
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//barraJugador.update();

		canvas.drawColor(Color.GRAY);
		barraJugador.draw(canvas);
		bola.draw(canvas);
		ladrillo.draw(canvas);
	}

	//	Metodo para actualizar en todo momento si los objetos colesionan con otros objetos
	public void update() {
		//		Colision bola vs barra usuario
		if(bola.getY() + bola.getBitmap().getHeight() >= barraJugador.getY()) {
			if(bola.getX() + bola.getBitmap().getWidth() > barraJugador.getX() && bola.getX() < barraJugador.getX() + barraJugador.getBitmap().getWidth()) {
				bola.setVy(-bola.getVy());
			}
		}
//		if(bola.getY() < ladrillo.getY() + ladrillo.getBitmap().getHeight()) {
//			if(bola.getX() + bola.getBitmap().getWidth() < ladrillo.getX() + ladrillo.getBitmap().getHeight() && bola.getX() < ladrillo.getX() + ladrillo.getBitmap().getWidth() + ladrillo.getBitmap().getHeight()) {
//				bola.setVy(-bola.getVy());
//			}
//		}
		
		
	}

}
