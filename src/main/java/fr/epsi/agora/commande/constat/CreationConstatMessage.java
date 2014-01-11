package fr.epsi.agora.commande.constat;

import java.util.UUID;

import org.joda.time.DateTime;

import fr.epsi.agora.commande.Message;

public class CreationConstatMessage implements Message {

	public CreationConstatMessage(String nom, String adresse, DateTime date, String geolocalisation, UUID utilisateur, UUID client) {
		this.nom = nom;
		this.adresse = adresse;
		this.date = date;
		this.geolocalisation = geolocalisation;
		this.utilisateur = utilisateur;
		this.client = client;
	}
	
	public final String nom;
	public final String adresse;
	public final DateTime date;
	public final String geolocalisation;
	public final UUID utilisateur;
	public final UUID client;
	
}
