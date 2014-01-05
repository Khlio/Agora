package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.FabriqueUtilisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;
import fr.epsi.agora.persistance.fake.AvecEntrepots;

public class ConnexionUtilisateurHandlerTest {

	@Rule
	public AvecEntrepots entrepots = new AvecEntrepots();
	
	@Test
	public void peutConnecterUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(new FabriqueUtilisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass"));
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
