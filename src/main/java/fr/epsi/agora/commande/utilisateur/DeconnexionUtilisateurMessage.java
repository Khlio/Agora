package fr.epsi.agora.commande.utilisateur;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class DeconnexionUtilisateurMessage implements Message {

	public DeconnexionUtilisateurMessage(UUID id) {
		this.id = id;
	}
	
	public final UUID id;
	
}
