package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class ModificationConstatMessage implements Message {

	public ModificationConstatMessage(UUID id, String nom, String date, String geolocalisation) {
		this.id = id;
		this.nom = nom;
		this.date = date;
		this.geolocalisation = geolocalisation;
	}
	
	public final UUID id;
	public final String nom;
	public final String date;
	public final String geolocalisation;
	
}
