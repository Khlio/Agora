package fr.epsi.agora.commande.constat;

import java.util.UUID;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.FabriqueConstat;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class CreationConstatHandler implements HandlerCommande<CreationConstatMessage> {

	@Override
	public UUID execute(CreationConstatMessage commande) {
		Utilisateur utilisateur = Entrepots.utilisateurs().get(commande.utilisateur).get();
		Client client = Entrepots.clients().get(commande.client).get();
		Constat constat = FabriqueConstat.nouveau(commande.nom, commande.adresse, commande.date, commande.geolocalisation, utilisateur, client);
		Entrepots.constats().ajoute(constat);
		return constat.getId();
	}

	@Override
	public Class<CreationConstatMessage> typeCommande() {
		return CreationConstatMessage.class;
	}

}
