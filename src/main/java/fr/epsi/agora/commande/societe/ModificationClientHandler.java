package fr.epsi.agora.commande.societe;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;

public class ModificationClientHandler implements HandlerCommande<ModificationClientMessage> {

	@Override
	public Object execute(ModificationClientMessage commande) {
		Client client = Entrepots.clients().get(commande.id).get();
		client.setNom(commande.nom);
		client.setPrenom(commande.prenom);
		client.setEmail(commande.email);
		client.setDateDeNaissance(commande.dateDeNaissance);
		client.setLieuDeNaissance(commande.lieuDeNaissance);
		client.setMetier(commande.metier);
		client.setNationalite(commande.nationalite);
		client.setAdresse(commande.adresse);
		client.setTelephone(commande.telephone);
		Entrepots.clients().modifie(client);
		return null;
	}

	@Override
	public Class<ModificationClientMessage> typeCommande() {
		return ModificationClientMessage.class;
	}

}
