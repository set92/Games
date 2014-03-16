package com.tobalsa.juegoevitarobstaculos;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{
	private static final String TAG = GameLoop.class.getSimpleName();

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;
	// The actual view that handles inputs
	// and draws to the surface
	private PintandoLienzo lienzo;

	// flag to hold game state 
	private boolean running;
	public void setRunning(boolean running) {
		this.running = running;
	}

	public GameLoop(SurfaceHolder surfaceHolder, PintandoLienzo lienzo) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.lienzo = lienzo;
	}

	@Override
	public void run() {
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (running) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					// update game state
					this.lienzo.update();
					// update game state 
					// render state to the screen
					// draws the canvas on the panel
					this.lienzo.onDraw(canvas);
				}
			} finally {
				// in case of an exception the surface is not left in 
				// an inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}
}
