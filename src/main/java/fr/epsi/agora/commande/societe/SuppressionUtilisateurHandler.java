package fr.epsi.agora.commande.societe;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class SuppressionUtilisateurHandler implements HandlerCommande<SuppressionUtilisateurMessage> {

	@Override
	public Object execute(SuppressionUtilisateurMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.idUtilisateur).get();
		Entrepots.utilisateurs().supprime(utilisateur);
		Entrepots.societes().get(commande.idSociete).get().supprimeUtilisateur(utilisateur.getId());
		return null;
	}

	@Override
	public Class<SuppressionUtilisateurMessage> typeCommande() {
		return SuppressionUtilisateurMessage.class;
	}

}
