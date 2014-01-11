package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionConstatMessage implements Message {

	public SuppressionConstatMessage(UUID id) {
		this.id = id;
	}
	
	public final UUID id;
	
}
