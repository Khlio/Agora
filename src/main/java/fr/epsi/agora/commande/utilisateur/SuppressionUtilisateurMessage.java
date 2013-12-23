package fr.epsi.agora.commande.utilisateur;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionUtilisateurMessage implements Message {

	public SuppressionUtilisateurMessage(UUID idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public final UUID idUtilisateur;
	
}
