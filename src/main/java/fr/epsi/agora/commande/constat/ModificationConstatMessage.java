package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class ModificationConstatMessage implements Message {

	public ModificationConstatMessage(UUID id, String nom, String adresse) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
	}
	
	public final UUID id;
	public final String nom;
	public final String adresse;
	
}
