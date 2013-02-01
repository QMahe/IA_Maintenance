package com.example.maintenance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button listMaintenances, quitter;
	String result = null;
	InputStream is = null;
	JSONObject json_data=null;
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	ArrayList<String> donnees = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		receptionMaintenances();
		setContentView(R.layout.activity_main);		
		// checkDataBase();
		
		this.listMaintenances = (Button) findViewById(R.id.buttonListMaintenances);
		this.quitter = (Button) findViewById(R.id.buttonQuitter);
        this.listMaintenances.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(), ListeMaintenancesActivity.class);
				startActivity(i);
			}
        	
        });
        
        this.quitter.setOnClickListener(new Button.OnClickListener(){
        	
        	@Override
        	public void onClick(View view) {
        		envoi();
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private boolean checkDataBase() {
	    SQLiteDatabase checkDB = null;
	    try {
	        checkDB = SQLiteDatabase.openDatabase("/data/data/com.example.maintenance/databases/inssetairlines.db", null, SQLiteDatabase.OPEN_READONLY);
	        checkDB.close();
	        String suc = "La BDD existe";
	        Toast.makeText(MainActivity.this, suc, Toast.LENGTH_SHORT).show();
	    } catch (SQLiteException e) {
	        // base de données n'existe pas.
	    	String exc = "La BDD n'existe pas";
	    	Toast.makeText(MainActivity.this, exc, Toast.LENGTH_SHORT).show();
	    }
	    return checkDB != null ? true : false;
	}
	
	public void receptionMaintenances() {
		try {
			HttpClient httpclient = new DefaultHttpClient();
    		HttpPost httppost = new HttpPost("http://192.168.1.10/Android/insset_airlines/index.php");
    		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    		HttpResponse response = httpclient.execute(httppost);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();
		}
    	catch(Exception e) {
    		Log.i("taghttppost",""+e.toString());
            Toast.makeText(getBaseContext(),e.toString() ,Toast.LENGTH_LONG).show();
        }
		
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
        	StringBuilder sb  = new StringBuilder();
        	String line = null;
        	while ((line = reader.readLine()) != null) {
        		sb.append(line + "\n");
        	}        	
        	is.close();
        	result = sb.toString();
        }
        catch(Exception e) {
        	Log.i("tagconvertstr",""+e.toString());
        	Toast.makeText(getBaseContext(),e.toString() ,Toast.LENGTH_LONG).show();
        }
        
        try {
        	JSONArray jArray = new JSONArray(result);
        	MaintenanceTable maintenanceTable = new MaintenanceTable(this);
        	maintenanceTable.Open();
        	for(int i=0;i<jArray.length();i++) {
        		json_data = jArray.getJSONObject(i);
        		Maintenance maintenance = new Maintenance();
        		int id = Integer.valueOf(json_data.getString("id_maintenance")).intValue();
        		maintenance.setId(id);
        		maintenance.setImmatriculation(json_data.getString("immatriculation"));
        		maintenance.setDatePrevue(json_data.getString("date_prevue"));
        		maintenance.setDateEff(json_data.getString("date_eff"));
        		maintenance.setDureePrevue(json_data.getString("duree_prevue"));
        		maintenance.setDureeEff(json_data.getString("duree_eff"));
        		maintenance.setNotes(json_data.getString("note"));
        		maintenanceTable.Save(maintenance);
        	}
        	maintenanceTable.Close();
        }
        catch(JSONException e) {
        	Log.i("tagjsonexp",""+e.toString());
        	Toast.makeText(getBaseContext(),e.toString() ,Toast.LENGTH_LONG).show();
        }
        catch (ParseException e) {
        	Log.i("tagjsonpars",""+e.toString());
        	Toast.makeText(getBaseContext(),e.toString() ,Toast.LENGTH_LONG).show();
        }
	}

	public void envoi() {
		Log.i("test","1");
		MaintenanceTable maintenanceTable = new MaintenanceTable(this);
		Log.i("test","2");
		maintenanceTable.Open();
		Log.i("test","3");
		Maintenance maintenance = (Maintenance) maintenanceTable.findCurrent();
		Log.i("test","4");
		maintenanceTable.Close();
		Log.i("test","5");
    	// Toast.makeText(MainActivity.this, maintenance.getId().toString(), Toast.LENGTH_SHORT).show();		
	}
}
