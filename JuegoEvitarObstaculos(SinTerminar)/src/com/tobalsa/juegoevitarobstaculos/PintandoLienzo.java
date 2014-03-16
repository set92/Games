package com.tobalsa.juegoevitarobstaculos;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.tobalsa.juegoevitarobstaculos.models.Column;
import com.tobalsa.juegoevitarobstaculos.models.Guy;
import com.tobalsa.juegoevitarobstaculos.models.Velocidad;

public class PintandoLienzo extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG= PintandoLienzo.class.getSimpleName(); 

	Random rand = new Random();
	
	private GameLoop thread;
	private Guy pj;
	private Column col;
	Paint paint = new Paint();

	public PintandoLienzo(Context contexto) {
		super(contexto);
		// Se añade el callback al contenedor del surface para interceptar los eventos
		getHolder().addCallback(this);
		// Se asigna el recurso y se guarda en una variable, intentar no hacer esto nunca en ejecucion
		pj= new Guy(BitmapFactory.decodeResource(getResources(), R.drawable.elaine), 150, 700);
		col = new Column(BitmapFactory.decodeResource(getResources(), R.drawable.columna), 1280, rand.nextInt(701));
		// Se crea el loop del juego
		thread = new GameLoop(getHolder(), this);

		setFocusable(true);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//		Canvas c = holder.lockCanvas(null);
		//		onDraw(c);
		//		holder.unlockCanvasAndPost(c);

		// El surface se ha creado y se puede iniciar el loop del juego
		thread.setRunning(true);
		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
//		this.width=width;
//		this.height=height;
//		Log.d(TAG, "W= "+width+" y H= "+height);

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// No se hace nada, y por lo tanto se intentara volver a terminar el hilo
			}
		}
		Log.d(TAG, "El hilo se cerro correctamente");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//super.onDraw(canvas);

		canvas.drawColor(Color.GRAY);
		pj.draw(canvas);
		col.draw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
//			pj.actionDown(event.getX(), event.getY());
			pj.getVelocidad().setyv(29);
			
			if (event.getY() > getHeight() - 50) {
				thread.setRunning(false);
				((Activity)getContext()).finish();
			} else {
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
			}

			break;
		case MotionEvent.ACTION_MOVE:
			pj.getVelocidad().setyv(29);

			break;
		case MotionEvent.ACTION_UP:
//			if (pj.isTouched()) {
//				pj.setTouched(false);
//			}
			pj.getVelocidad().setyv(-20);

			break;
		}

		return true;
	}
	
//	Metodo para actualizar en todo momento si los objetos colesionan con los bordes de la pantalla
	public void update() {
		//Choque con la pared superior
		if (pj.getY() - pj.getBitmap().getHeight() / 2 <= 0) {
			Log.d(TAG, "Ha tocado techo");
			pj.getVelocidad().setyv(0);
			pj.getVelocidad().setxv(0);
			pj.setY((float) ((0 + pj.getBitmap().getHeight() / 2) + 0.1));
		}
		//Choque con el suelo
		if (pj.getVelocidad().getDireccionEnY() == Velocidad.DIRECCION_ABAJO && pj.getY() + pj.getBitmap().getHeight() / 2 >= getHeight()) {
//			pj.getVelocidad().cambiarDireccionEnY();
			Log.d(TAG, "Ha tocado suelo");
			pj.getVelocidad().setyv(0);
			pj.getVelocidad().setxv(0);
			//Aplicando el -0.1 hacemos que el usuario piense que esta en el suelo, casteamos a float porque 0.1 no entra en el rango de los float
			pj.setY((float) ((getHeight()-pj.getBitmap().getHeight()/2)-0.1)); 
		}
		pj.update();
		
		col.getVelocidad().setxv(-29);
		col.getVelocidad().setDireccionEnX(Velocidad.DIRECCION_IZQUIERDA);
		if (col.getVelocidad().getDireccionEnX() == Velocidad.DIRECCION_IZQUIERDA && col.getX() - col.getBitmap().getWidth() <= 0) {
			col.setX(getWidth());
			col.setY(rand.nextInt(701));
		}
		col.update();
		
		if (pj.getY() == col.getX()) {
			Log.d(TAG, "CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE ");
			Log.d(TAG, "CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE ");
			Log.d(TAG, "CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE ");
			Log.d(TAG, "CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE CHOQUE ");
		}
		
	}




}
