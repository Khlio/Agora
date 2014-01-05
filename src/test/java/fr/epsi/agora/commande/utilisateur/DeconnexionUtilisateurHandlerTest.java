package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.FabriqueUtilisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;
import fr.epsi.agora.persistance.fake.AvecEntrepots;

public class DeconnexionUtilisateurHandlerTest {

	@Rule
	public AvecEntrepots entrepots = new AvecEntrepots();
	
	@Test
	public void peutDeconnecterUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(new FabriqueUtilisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass"));
		utilisateur.setConnecte(true);
		assertThat(utilisateur.isConnecte()).isTrue();
		DeconnexionUtilisateurMessage commande = new DeconnexionUtilisateurMessage(utilisateur.getId());
		
		new DeconnexionUtilisateurHandler().execute(commande);
		
		Utilisateur utilisateurConnecte = Entrepots.utilisateurs().get(utilisateur.getId()).orNull();
		assertThat(utilisateurConnecte).isNotNull();
		assertThat(utilisateurConnecte.isConnecte()).isFalse();
	}
	
}
