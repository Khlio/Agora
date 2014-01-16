package fr.epsi.agora.commande.constat;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;

import fr.epsi.agora.commande.Message;

public class CreationConstatMessage implements Message {

	public CreationConstatMessage(String nom, String adresse1, String adresse2, String codePostal, DateTime date, String geolocalisation,
			UUID utilisateur, UUID client, List<String> audios, List<String> annexes) {
		this.nom = nom;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.date = date;
		this.geolocalisation = geolocalisation;
		this.utilisateur = utilisateur;
		this.client = client;
		this.audios = audios;
		this.annexes = annexes;
	}
	
	public final String nom;
	public final String adresse1;
	public final String adresse2;
	public final String codePostal;
	public final DateTime date;
	public final String geolocalisation;
	public final UUID utilisateur;
	public final UUID client;
	public final List<String> audios;
	public final List<String> annexes;
	
}
