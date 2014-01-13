package fr.epsi.agora.commande.societe;

import static com.google.common.base.Preconditions.checkState;

import java.util.UUID;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FabriqueClient;
import fr.epsi.agora.domaine.societe.Societe;

public class AjoutClientHandler implements HandlerCommande<AjoutClientMessage> {

	@Override
	public UUID execute(AjoutClientMessage commande) {
		Optional<Societe> societe = Entrepots.societes().get(commande.idSociete);
		checkState(societe.isPresent(), "Société inconnue");
		Client client = FabriqueClient.nouveau(commande.nom, commande.prenom, commande.email, commande.dateDeNaissance, commande.lieuDeNaissance,
				commande.metier, commande.nationalite, commande.adresse1, commande.adresse2, commande.codePostal, commande.telephonePortable, commande.telephoneFixe);
		societe.get().ajouteClient(client.getId());
		Entrepots.clients().ajoute(client);
		return client.getId();
	}

	@Override
	public Class<AjoutClientMessage> typeCommande() {
		return AjoutClientMessage.class;
	}

}
