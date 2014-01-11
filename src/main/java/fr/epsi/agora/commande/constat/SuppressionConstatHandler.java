package fr.epsi.agora.commande.constat;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;

public class SuppressionConstatHandler implements HandlerCommande<SuppressionConstatMessage> {

	@Override
	public Object execute(SuppressionConstatMessage commande) {
		Constat constat = Entrepots.constats().get(commande.id).get();
		Entrepots.constats().supprime(constat);
		return null;
	}

	@Override
	public Class<SuppressionConstatMessage> typeCommande() {
		return SuppressionConstatMessage.class;
	}

}
