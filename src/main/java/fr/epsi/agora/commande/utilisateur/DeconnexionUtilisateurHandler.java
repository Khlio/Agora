package fr.epsi.agora.commande.utilisateur;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class DeconnexionUtilisateurHandler implements HandlerCommande<DeconnexionUtilisateurMessage> {

	@Override
	public Object execute(DeconnexionUtilisateurMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.id).get();
		utilisateur.setConnecte(false);
		Entrepots.utilisateurs().modifie(utilisateur);
		return null;
	}

	@Override
	public Class<DeconnexionUtilisateurMessage> typeCommande() {
		return DeconnexionUtilisateurMessage.class;
	}

}
