package com.example.maintenance;

import java.util.HashMap;

public class Maintenance {
	
	// Définition des infos concernant une maintenance
	private Integer id_maintenance;
	private String immatriculation;
	private String date_prevue;
	private String date_eff;
	private String duree_prevue;
	private String duree_eff;
	private String notes;
	
	public Maintenance() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id_maintenance;
	}
	
	public void setId(Integer id) {
		this.id_maintenance = id;
	}
	
	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	
	public String getDatePrevue() {
		return date_prevue;
	}
	
	public void setDatePrevue(String date_prevue) {
		this.date_prevue = date_prevue;
	}
	
	public String getDateEff() {
		return date_eff;
	}
	
	public void setDateEff(String date_eff) {
		this.date_eff = date_eff;
	}
	
	public String getDureePrevue() {
		return duree_prevue;
	}
	
	public void setDureePrevue(String duree_prevue) {
		this.duree_prevue = duree_prevue;
	}
	
	public String getDureeEff() {
		return duree_eff;
	}
	
	public void setDureeEff(String duree_eff) {
		this.duree_eff = duree_eff;
	}
	
	public String getNotes() {
		return notes;
	}
	
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public HashMap<String, String> toArray() {
		HashMap<String, String> array = new HashMap<String, String>();
		array.put("id", String.valueOf(this.getId()));
		array.put("immatriculation", this.getImmatriculation());
		array.put("date_prevue", this.getDatePrevue());
		array.put("date_eff", this.getDateEff());
		array.put("duree_prevue", this.getDureePrevue());
		array.put("duree_eff", this.getDureeEff());
		array.put("notes", this.getNotes());
		
		return array;		
	}
}
