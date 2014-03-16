package com.tobalsa.arkanoidandroid;

import com.tobalsa.arkanoidandroid.models.Ball;

public class BallThread extends Thread {

	private Ball bola;
	private boolean run;
	private float speed;

	public BallThread(Ball bola) {
		this.bola = bola;
		this.run = false;
		this.speed = (float) 0;
	}

	public void setRunning(boolean run) {
		this.run = run;
	}

	@Override
	public void run() {
		while(run) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bola.move(speed, speed);
		}
	}

}
