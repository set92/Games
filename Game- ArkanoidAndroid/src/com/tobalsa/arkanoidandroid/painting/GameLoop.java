package com.tobalsa.arkanoidandroid.painting;



import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{
	private static final String TAG= "ARKANOID_ANDROID"; 

	private SurfaceHolder surfaceHolder;
	private GameView gameView;
	private boolean running;
	static final long FPS = 60;

	public void setRunning(boolean running) {
		this.running = running;
	}

	public GameLoop(SurfaceHolder surfaceHolder, GameView vistaJuego) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gameView = vistaJuego;
	}

	@Override
	public void run() {
		Canvas canvas;
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		Log.d(TAG, "Iniciando juego");
		while (running) {
			canvas = null;
			startTime = System.currentTimeMillis();
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					// Actualizamos los componentes, colisiones...
					this.gameView.getBarraJugador().update();
					this.gameView.getBola().update();
					this.gameView.update();
					// Pintamos los objetos, el fondo...
					this.gameView.onDraw(canvas);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	
			sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0){
					sleep(sleepTime);
				} else{
					sleep(10);
				}

			} catch (Exception e) {

			}

		}
	}
}
