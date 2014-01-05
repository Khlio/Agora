package fr.epsi.agora.commande.utilisateur;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class ModificationUtilisateurHandler implements HandlerCommande<ModificationUtilisateurMessage> {

	@Override
	public Object execute(ModificationUtilisateurMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.id).get();
		if (null != commande.nom) {
			utilisateur.setNom(commande.nom);
		}
		if (null != commande.prenom) {
			utilisateur.setPrenom(commande.prenom);
		}
		if (null != commande.email) {
			utilisateur.setEmail(commande.email);
		}
		if (null != commande.motDePasse) {
			utilisateur.setMotDePasse(commande.motDePasse);
		}
		if (null != commande.adresse) {
			utilisateur.setAdresse(commande.adresse);
		}
		if (null != commande.telephone) {
			utilisateur.setTelephone(commande.telephone);
		}
		Entrepots.utilisateurs().modifie(utilisateur);
		return null;
	}

	@Override
	public Class<ModificationUtilisateurMessage> typeCommande() {
		return ModificationUtilisateurMessage.class;
	}

}
