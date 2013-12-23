package fr.epsi.agora.commande.utilisateur;

import java.util.UUID;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.FabriqueUtlisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class CreationUtilisateurHandler implements HandlerCommande<CreationUtilisateurMessage> {

	@Override
	public UUID execute(CreationUtilisateurMessage commande) {
		Utilisateur utilisateur = new FabriqueUtlisateur().nouveau(
				commande.nom, commande.prenom, commande.email, commande.motDePasse, commande.adresse, commande.telephone);
		Entrepots.utilisateurs().ajoute(utilisateur);
		return utilisateur.getId();
	}

	@Override
	public Class<CreationUtilisateurMessage> typeCommande() {
		return CreationUtilisateurMessage.class;
	}

}
