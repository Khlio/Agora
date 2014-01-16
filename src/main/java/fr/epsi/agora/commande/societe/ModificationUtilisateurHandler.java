package fr.epsi.agora.commande.societe;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class ModificationUtilisateurHandler implements HandlerCommande<ModificationUtilisateurMessage> {

	@Override
	public Object execute(ModificationUtilisateurMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.id).get();
		utilisateur.setNom(commande.nom);
		utilisateur.setPrenom(commande.prenom);
		utilisateur.setMotDePasse(commande.motDePasse);
		utilisateur.setAdresse(commande.adresse);
		utilisateur.setCodePostal(commande.codePostal);
		utilisateur.setTelephone(commande.telephone);
		Entrepots.utilisateurs().modifie(utilisateur);
		return null;
	}

	@Override
	public Class<ModificationUtilisateurMessage> typeCommande() {
		return ModificationUtilisateurMessage.class;
	}

}
