package com.example.maintenance;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// RECUPERER LA DATE
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("EEEE dd M");
		String dateString = dateFormat1.format(gc.getTime());
		Toast.makeText(MainActivity.this, dateString, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
