package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionMediaAudioMessage implements Message {

	public SuppressionMediaAudioMessage(UUID idConstat, String audio) {
		this.idConstat = idConstat;
		this.audio = audio;
	}
	
	public final UUID idConstat;
	public final String audio;
	
}
