package com.example.maintenance;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Table {
	 protected SQLiteDatabase maBDD;
	 protected SQLiteOpenHelper sqLiteOpenHelper;
	 
	 public Table() {
		// TODO Auto-generated constructor stub
	 }
	
	 // Ouverture de la connexion à la base
	 public void Open() {
	     maBDD = sqLiteOpenHelper.getWritableDatabase();
	 }
	 
	 // Fermeture de la connexion à la base
	 public void Close() {
	     maBDD.close();
	 }	 
}
