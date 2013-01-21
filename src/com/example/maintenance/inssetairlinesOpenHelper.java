package com.example.maintenance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class inssetairlinesOpenHelper extends SQLiteOpenHelper {
	
	// Version de la base de données
	private static final int DATABASE_VERSION = 1;
	
	// Nom de la base de données
	private static final String DATABASE_NAME = "inssetairlines.db";

	// Nom de la table de maintenance
	private static final String DATABASE_TABLE = "maintenance";
	
	// Description des colonnes
	public static final String COLUMN_ID_MAINTENANCE = "id_maintenance";
	public static final int NUM_COLUMN_ID_MAINTENANCE = 0;
	public static final String COLUMN_IMMATRICULATION = "immatriculation";
	public static final int NUM_COLUMN_IMMATRICULATION = 1;
	public static final String COLUMN_DATE_PREVUE = "date_prevue";
	public static final int NUM_COLUMN_DATE_PREVUE = 2;
	public static final String COLUMN_DATE_EFF = "date_eff";
	public static final int NUM_COLUMN_DATE_EFF = 3;
	public static final String COLUMN_DUREE_PREVUE = "duree_prevue";
	public static final int NUM_COLUMN_DUREE_PREVUE = 4;
	public static final String COLUMN_DUREE_EFF = "duree_eff";
	public static final int NUM_COLUMN_DUREE_EFF = 5;
	public static final String COLUMN_NOTES = "notes";
	public static final int NUM_COLUMN_NOTES = 6;
	
	// Requête SQL de création de la base
	public static final String REQUETE_CREATION_BDD = "create table " + DATABASE_TABLE + " (" + COLUMN_ID_MAINTENANCE 
			+ " integer primary key autoincrement, " + COLUMN_IMMATRICULATION + " text not null, " + COLUMN_DATE_PREVUE + " text not null, " 
			+ COLUMN_DATE_EFF + " text not null, " + COLUMN_DUREE_PREVUE + " text not null, " + COLUMN_DUREE_EFF + " text not null, " 
			+ COLUMN_NOTES + " text not null);";
	
	public inssetairlinesOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, DATABASE_NAME, factory, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_BDD);
		// TODO Auto-generated method stub		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if (newVersion > DATABASE_VERSION) {
			// Inscrit la mise à jour de version dans le journal
			Log.w("TaskDBAdapter", "Mise à jour de la version " + oldVersion + " vers la version " + newVersion + ", ce qui détruira toutes les anciennes données.");
			db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		// TODO Auto-generated method stub
		
	}

}
