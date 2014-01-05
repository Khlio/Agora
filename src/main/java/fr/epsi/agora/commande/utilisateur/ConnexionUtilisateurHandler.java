package fr.epsi.agora.commande.utilisateur;

import java.util.Date;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class ConnexionUtilisateurHandler implements HandlerCommande<ConnexionUtilisateurMessage> {

	@Override
	public Object execute(ConnexionUtilisateurMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.id).get();
		utilisateur.setConnecte(true);
		utilisateur.setDerniereConnexion(new Date());
		Entrepots.utilisateurs().modifie(utilisateur);
		return null;
	}

	@Override
	public Class<ConnexionUtilisateurMessage> typeCommande() {
		return ConnexionUtilisateurMessage.class;
	}

}
