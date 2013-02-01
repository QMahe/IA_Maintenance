package com.example.maintenance;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class MaintenanceTable extends Table {
	
	public MaintenanceTable(Context context) {
		sqLiteOpenHelper = new inssetairlinesOpenHelper(context, null);
	}
	
	public void Delete(int id) {
		maBDD.delete(inssetairlinesOpenHelper.DATABASE_TABLE, inssetairlinesOpenHelper.COLUMN_ID_MAINTENANCE + "=?", new String[] { String.valueOf(id) });
	}
	
	public List<Maintenance> findAll() {
        // Récupération de la liste des maintenances
        Cursor cursor = maBDD.query(inssetairlinesOpenHelper.DATABASE_TABLE,
		new String[] { inssetairlinesOpenHelper.COLUMN_ID_MAINTENANCE,
                       inssetairlinesOpenHelper.COLUMN_IMMATRICULATION,
                       inssetairlinesOpenHelper.COLUMN_DATE_PREVUE,
                       inssetairlinesOpenHelper.COLUMN_DATE_EFF,
                       inssetairlinesOpenHelper.COLUMN_DUREE_PREVUE,
                       inssetairlinesOpenHelper.COLUMN_DUREE_EFF,
					   inssetairlinesOpenHelper.COLUMN_NOTES }, null, null, null, null, null, null);

        return ConvertCursorToListObject(cursor);
    }
	
	public List<Maintenance> findNew() {
        // Récupération de la liste des maintenances
		String query = "select * from "+inssetairlinesOpenHelper.DATABASE_TABLE+" where "+inssetairlinesOpenHelper.COLUMN_DUREE_EFF+" = 0"; 
		Cursor cursor = maBDD.rawQuery(query, null);
        return ConvertCursorToListObject(cursor);
    }
	
	public Maintenance findCurrent() {
		// Récupération de la maintenance en cours
		String query = "select * from "+inssetairlinesOpenHelper.DATABASE_TABLE+" where "+inssetairlinesOpenHelper.COLUMN_DATE_EFF+" != 0";
		Cursor cursor = maBDD.rawQuery(query, null);
		return ConvertCursorToObject(cursor);
	}
	
	public List<Maintenance> ConvertCursorToListObject(Cursor c) {
		List<Maintenance> liste = new ArrayList<Maintenance>();		
        if (c.getCount() == 0) {
        	return liste;
        }
        
        // position sur le premier item
        c.moveToFirst();

        // Pour chaque item
        do {
        	Maintenance maintenance = ConvertCursorToObject(c);
        	liste.add(maintenance);
        }
        while (c.moveToNext());

        // Fermeture du curseur
        c.close();
        return liste;
    }
	
    public Maintenance ConvertCursorToObject(Cursor c) {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(c.getInt(inssetairlinesOpenHelper.NUM_COLUMN_ID_MAINTENANCE));
        maintenance.setImmatriculation(c.getString(inssetairlinesOpenHelper.NUM_COLUMN_IMMATRICULATION));
        maintenance.setDatePrevue(c.getString(inssetairlinesOpenHelper.NUM_COLUMN_DATE_PREVUE));
        maintenance.setDateEff(c.getString(inssetairlinesOpenHelper.NUM_COLUMN_DATE_EFF));
        maintenance.setDureePrevue(c.getString(inssetairlinesOpenHelper.NUM_COLUMN_DUREE_PREVUE));
        maintenance.setDureeEff(c.getString(inssetairlinesOpenHelper.NUM_COLUMN_DUREE_EFF));
        maintenance.setNotes(c.getString(inssetairlinesOpenHelper.NUM_COLUMN_NOTES));
        
        return maintenance;
    }
    
    public void Save(Maintenance entite) {
    	ContentValues contentValues = new ContentValues();
    	contentValues.put(inssetairlinesOpenHelper.COLUMN_ID_MAINTENANCE, entite.getId());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_IMMATRICULATION, entite.getImmatriculation());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DATE_PREVUE, entite.getDatePrevue());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DATE_EFF, entite.getDateEff());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DUREE_PREVUE, entite.getDureePrevue());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DUREE_EFF, entite.getDureeEff());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_NOTES, entite.getNotes());
        maBDD.insert(inssetairlinesOpenHelper.DATABASE_TABLE, null, contentValues);
    }
    
	public void Update(Maintenance entite) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(inssetairlinesOpenHelper.COLUMN_IMMATRICULATION, entite.getImmatriculation());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DATE_PREVUE, entite.getDatePrevue());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DATE_EFF, entite.getDateEff());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DUREE_PREVUE, entite.getDureePrevue());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_DUREE_EFF, entite.getDureeEff());
        contentValues.put(inssetairlinesOpenHelper.COLUMN_NOTES, entite.getNotes());
        maBDD.update(inssetairlinesOpenHelper.DATABASE_TABLE, contentValues, inssetairlinesOpenHelper.COLUMN_ID_MAINTENANCE + "=?", new String[] { String.valueOf(entite.getId()) });
    }
}
