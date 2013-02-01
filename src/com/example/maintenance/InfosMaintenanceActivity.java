package com.example.maintenance;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class InfosMaintenanceActivity extends Activity {
	private Button save, start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_maintenance);
		
		this.save = (Button) findViewById(R.id.buttonSave);
		this.start = (Button) findViewById(R.id.buttonDemarrer);
	}

}
