package fr.epsi.agora.commande.societe;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FabriqueClient;
import fr.epsi.agora.domaine.societe.Societe;

public class AjoutClientHandler implements HandlerCommande<AjoutClientMessage> {

	@Override
	public Object execute(AjoutClientMessage commande) {
		Optional<Societe> societe = Entrepots.societes().get(commande.idSociete);
		checkState(societe.isPresent(), "Société inconnue");
		societe.get().ajouteClient(FabriqueClient.nouveau(commande.nom, commande.prenom, commande.email, commande.dateDeNaissance, commande.lieuDeNaissance,
				commande.metier, commande.nationalite, commande.adresse, commande.telephone));
		return null;
	}

	@Override
	public Class<AjoutClientMessage> typeCommande() {
		return AjoutClientMessage.class;
	}

}
