package fr.epsi.agora.commande.societe;

import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Societe;

public class AjoutUtilisateurHandler implements HandlerCommande<AjoutUtilisateurMessage> {

	@Override
	public Object execute(AjoutUtilisateurMessage commande) {
		Optional<Societe> societe = Entrepots.societes().get(commande.idSociete);
		checkState(societe.isPresent(), "Société inconnue");
		societe.get().ajouteUtilisateur(FabriqueUtilisateur.nouveau(commande.nom, commande.prenom, commande.email, commande.motDePasse,
				commande.adresse, commande.telephone));
		return null;
	}

	@Override
	public Class<AjoutUtilisateurMessage> typeCommande() {
		return AjoutUtilisateurMessage.class;
	}

}
