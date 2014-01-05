package fr.epsi.agora.commande.utilisateur;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class ConnexionUtilisateurMessage implements Message {

	public ConnexionUtilisateurMessage(UUID id) {
		this.id = id;
	}
	
	public final UUID id;
	
}
