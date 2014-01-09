package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FabriqueSociete;
import fr.epsi.agora.domaine.societe.Societe;

public class CreationSocieteHandler implements HandlerCommande<CreationSocieteMessage> {

	@Override
	public UUID execute(CreationSocieteMessage commande) {
		Societe societe = FabriqueSociete.nouveau(commande.siret, commande.nom);
		Entrepots.societes().ajoute(societe);
		return societe.getId();
	}

	@Override
	public Class<CreationSocieteMessage> typeCommande() {
		return CreationSocieteMessage.class;
	}

}
