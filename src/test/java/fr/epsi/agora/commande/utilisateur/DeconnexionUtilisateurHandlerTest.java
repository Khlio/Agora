package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class DeconnexionUtilisateurHandlerTest extends HandlerCommandeRegle {
	
	@Test
	public void peutDeconnecterUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(FakeFabriqueUtilisateur.nouveau());
		utilisateur.setConnecte(true);
		assertThat(utilisateur.isConnecte()).isTrue();
		DeconnexionUtilisateurMessage commande = new DeconnexionUtilisateurMessage(utilisateur.getId());
		
		new DeconnexionUtilisateurHandler().execute(commande);
		
		Utilisateur utilisateurConnecte = Entrepots.utilisateurs().get(utilisateur.getId()).orNull();
		assertThat(utilisateurConnecte).isNotNull();
		assertThat(utilisateurConnecte.isConnecte()).isFalse();
	}
	
}
