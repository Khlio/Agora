package fr.epsi.agora.commande.societe;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Societe;

public class SuppressionSocieteHandler implements HandlerCommande<SuppressionSocieteMessage> {

	@Override
	public Object execute(SuppressionSocieteMessage commande) {
		Societe societe = Entrepots.societes().get(commande.id).get();
		Entrepots.societes().supprime(societe);
		return null;
	}

	@Override
	public Class<SuppressionSocieteMessage> typeCommande() {
		return SuppressionSocieteMessage.class;
	}

}
