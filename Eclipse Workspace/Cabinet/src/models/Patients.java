package models;

import java.sql.Date;

public class Patients {
	int id ;
	String nom;
	String prenom;
	Date dateNais;
	String age;
	String adresse;
	String tel;
	
	public Patients() {
		super();
	}
    public Patients(int id , String nom , String prenom , Date date , String age , String adresse , String tel) {
    	
    	this.id=id;
    	this.nom=nom;
    	this.prenom=prenom;
    	this.dateNais=date;
    	this.age=age;
    	this.adresse=adresse;
    	this.tel=tel;
    	
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNais() {
		return dateNais;
	}
	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
    
    
}
