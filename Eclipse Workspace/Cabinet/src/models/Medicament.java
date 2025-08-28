package models;


public class Medicament {
	int id ;
	String denom;
	String groupe;
	String comp;
	String dose;
	
	public Medicament() {
		super();
	}
    public Medicament(int id , String denom , String groupe,String comp,String dose ) {
    	
    	this.id=id;
    	this. denom= denom;
    	this.groupe=groupe;
    	this.comp=comp;
    	this.dose=dose;
    	
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}}
