package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class SuppressionMediaAnnexeMessage implements Message {

	public SuppressionMediaAnnexeMessage(UUID idConstat, String annexe) {
		this.idConstat = idConstat;
		this.annexe = annexe;
	}
	
	public final UUID idConstat;
	public final String annexe;
	
}
