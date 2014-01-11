package fr.epsi.agora.domaine.constat;

import java.util.UUID;

import org.joda.time.DateTime;

import fr.epsi.agora.domaine.Aggregat;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class Constat implements Aggregat {

	protected Constat() {
	}
	
	public Constat(UUID id) {
		this.id = id;
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public String getGeolocalisation() {
		return geolocalisation;
	}

	public void setGeolocalisation(String geolocalisation) {
		this.geolocalisation = geolocalisation;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private UUID id;
	private String nom;
	private String adresse;
	private DateTime date;
	private String geolocalisation;
	private Utilisateur utilisateur;
	private Client client;

}
