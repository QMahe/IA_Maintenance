package com.example.maintenance;

import java.util.ArrayList;
import java.sql.Date;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListeMaintenancesActivity extends ListActivity {
	ListView liste = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_maintenances);
		
		liste = (ListView) findViewById(android.R.id.list);
		
		MaintenanceTable maintenanceTable = new MaintenanceTable(getApplicationContext());
		maintenanceTable.Open();
		ArrayList<Maintenance> maintenances = (ArrayList<Maintenance>) maintenanceTable.findNew();
		maintenanceTable.Close();
		
		Log.i("Nombre de donnees", String.valueOf(maintenances.size()));
		
		if (maintenances.size() > 0) {
			String[] tabMaintenance = new String[maintenances.size()];
			int id = 0;
			for (Maintenance m : maintenances) {
				long timestamp = Long.parseLong(m.getDatePrevue());
				Date date = new Date(timestamp * 1000);
				tabMaintenance[id++] = m.getImmatriculation()+" - "+date;
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, tabMaintenance);
			liste.setAdapter(adapter);
		}
		else {
			Toast.makeText(getBaseContext(), "Pas de maintenance prévue", Toast.LENGTH_SHORT).show();
		}
		
		/*liste.setOnClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		}*/
	}
}