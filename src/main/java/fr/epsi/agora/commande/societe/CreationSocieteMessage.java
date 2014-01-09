package fr.epsi.agora.commande.societe;

import fr.epsi.agora.commande.Message;

public class CreationSocieteMessage implements Message {

	public CreationSocieteMessage(String siret, String nom) {
		this.siret = siret;
		this.nom = nom;
	}
	
	public final String siret;
	public final String nom;
	
}
