package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Societe;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class SuppressionUtilisateurHandlerTest extends HandlerCommandeRegle {
	
	@Test
	public void peutSupprimerUtilisateur() {
		Societe societe = Entrepots.societes().ajoute(FakeFabriqueSociete.nouveau());
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(FakeFabriqueUtilisateur.nouveau());
		societe.ajouteUtilisateur(utilisateur.getId());
		SuppressionUtilisateurMessage commande = new SuppressionUtilisateurMessage(utilisateur.getId(), societe.getId());
		
		new SuppressionUtilisateurHandler().execute(commande);
		
		assertThat(Entrepots.utilisateurs().get(utilisateur.getId()).orNull()).isNull();
		assertThat(Entrepots.societes().get(societe.getId()).get().getUtilisateurs()).isEmpty();
	}
	
}
