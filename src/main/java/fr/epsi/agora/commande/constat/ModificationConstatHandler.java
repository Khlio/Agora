package fr.epsi.agora.commande.constat;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;

public class ModificationConstatHandler implements HandlerCommande<ModificationConstatMessage> {

	@Override
	public Object execute(ModificationConstatMessage commande) {
		Constat constat = Entrepots.constats().get(commande.id).get();
		constat.setNom(commande.nom);
		constat.setAdresse1(commande.adresse1);
		constat.setAdresse2(commande.adresse2);
		constat.setCodePostal(commande.codePostal);
		Entrepots.constats().modifie(constat);
		return null;
	}

	@Override
	public Class<ModificationConstatMessage> typeCommande() {
		return ModificationConstatMessage.class;
	}

}
