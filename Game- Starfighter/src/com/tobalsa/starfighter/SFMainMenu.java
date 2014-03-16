package com.tobalsa.starfighter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SFMainMenu extends Activity {

	private Button btnStart, btnExit;

	final SFEngine engine = new SFEngine();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		SFEngine.musicThread = new Thread(new Thread(){
			@Override
			public void run() {
				Intent bgMusic = new Intent(getApplicationContext(), SFMusic.class);
				startService(bgMusic);
				SFEngine.context =getApplicationContext();
			}
		});
		SFEngine.musicThread.start();
		
		
		
		
		
		Intent startGame = new Intent(getApplicationContext(), SFGame.class);
		startActivity(startGame);
		
		
		
		
		
		btnStart = (Button) findViewById(R.id.btnStart);
		btnExit = (Button) findViewById(R.id.btnExit);

		btnStart.getBackground().setAlpha(SFEngine.MENU_BUTTON_ALPHA);
		btnStart.setHapticFeedbackEnabled(SFEngine.HAPTIC_BUTTON_FEEDBACK);

		btnExit.getBackground().setAlpha(SFEngine.MENU_BUTTON_ALPHA);
		btnExit.setHapticFeedbackEnabled(SFEngine.HAPTIC_BUTTON_FEEDBACK);

		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent startGame = new Intent(getApplicationContext(), SFGame.class);
				startActivity(startGame);
			}
		});

		btnExit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean clean = false;
				clean = engine.onExit(v);
				if (clean) {
					int pid = android.os.Process.myPid();
					android.os.Process.killProcess(pid);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_menu, menu);
		return true;
	}

}

