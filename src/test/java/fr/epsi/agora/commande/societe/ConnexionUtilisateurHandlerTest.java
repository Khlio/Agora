package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class ConnexionUtilisateurHandlerTest extends HandlerCommandeRegle {
	
	@Test
	public void peutConnecterUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(FakeFabriqueUtilisateur.nouveau());
		assertThat(utilisateur.isConnecte()).isFalse();
		assertThat(utilisateur.getDerniereConnexion()).isNull();
		ConnexionUtilisateurMessage commande = new ConnexionUtilisateurMessage(utilisateur.getId());
		
		new ConnexionUtilisateurHandler().execute(commande);
		
		Utilisateur utilisateurConnecte = Entrepots.utilisateurs().get(utilisateur.getId()).orNull();
		assertThat(utilisateurConnecte).isNotNull();
		assertThat(utilisateurConnecte.isConnecte()).isTrue();
		assertThat(utilisateurConnecte.getDerniereConnexion()).isNotNull();
	}
	
}
