package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionClientMessage implements Message {

	public SuppressionClientMessage(UUID id) {
		this.id = id;
	}
	
	public final UUID id;
	
}
