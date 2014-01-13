package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionClientMessage implements Message {

	public SuppressionClientMessage(UUID idClient, UUID idSociete) {
		this.idClient = idClient;
		this.idSociete = idSociete;
	}
	
	public final UUID idClient;
	public final UUID idSociete;
	
}
