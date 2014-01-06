package fr.epsi.agora.domaine.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class UtilisateurTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerCesAttributs() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateur.getAdresse()).isEqualTo("1 rue Test");
		assertThat(utilisateur.getTelephone()).isEqualTo("0607080910");
		assertThat(utilisateur.getDerniereConnexion()).isNull();
		assertThat(utilisateur.isConnecte()).isFalse();
	}
	
}
