package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionSocieteMessage implements Message {

	public SuppressionSocieteMessage(UUID id) {
		this.id = id;
	}
	
	public final UUID id;
	
}
