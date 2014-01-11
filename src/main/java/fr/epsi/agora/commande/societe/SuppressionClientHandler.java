package fr.epsi.agora.commande.societe;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;

public class SuppressionClientHandler implements HandlerCommande<SuppressionClientMessage> {

	@Override
	public Object execute(SuppressionClientMessage commande) {
		Client client = Entrepots.clients().get(commande.id).get();
		Entrepots.clients().supprime(client);
		return null;
	}

	@Override
	public Class<SuppressionClientMessage> typeCommande() {
		return SuppressionClientMessage.class;
	}

}
