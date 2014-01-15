package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class ModificationConstatMessage implements Message {

	public ModificationConstatMessage(UUID id, String nom, String adresse1, String adresse2, String codePostal) {
		this.id = id;
		this.nom = nom;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
	}
	
	public final UUID id;
	public final String nom;
	public final String adresse1;
	public final String adresse2;
	public final String codePostal;
	
}
