package fr.epsi.agora.commande.utilisateur;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class SuppressionUtilisateurHandler implements HandlerCommande<SuppressionUtilisateurMessage> {

	@Override
	public Object execute(SuppressionUtilisateurMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.idUtilisateur).get();
		Entrepots.utilisateurs().supprime(utilisateur);
		return null;
	}

	@Override
	public Class<SuppressionUtilisateurMessage> typeCommande() {
		return SuppressionUtilisateurMessage.class;
	}

}