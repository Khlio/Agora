package fr.epsi.agora.requete.client;

import org.jongo.marshall.jackson.oid.Id;

public class ResumeClient {

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
	@Id
	private String id;
	private String nom;
	private String prenom;
	
}
