package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionUtilisateurMessage implements Message {

	public SuppressionUtilisateurMessage(UUID idUtilisateur, UUID idSociete) {
		this.idUtilisateur = idUtilisateur;
		this.idSociete = idSociete;
	}
	
	public final UUID idUtilisateur;
	public final UUID idSociete;
	
}
