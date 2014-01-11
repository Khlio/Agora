package fr.epsi.agora.requete.constat;

import org.jongo.marshall.jackson.oid.Id;

import fr.epsi.agora.requete.societe.DetailsClient;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;

public class DetailsConstat {
	
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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getGeolocalisation() {
		return geolocalisation;
	}
	
	public void setGeolocalisation(String geolocalisation) {
		this.geolocalisation = geolocalisation;
	}
	
	public DetailsUtilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public void setUtilisateur(DetailsUtilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	public DetailsClient getClient() {
		return client;
	}
	
	public void setClient(DetailsClient client) {
		this.client = client;
	}
	
	@Id
	private String id;
	private String nom;
	private String date;
	private String geolocalisation;
	private DetailsUtilisateur utilisateur;
	private DetailsClient client;
	
}
