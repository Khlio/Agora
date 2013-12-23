package fr.epsi.agora.domaine.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class UtilisateurTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Utilisateur utilisateur = unUtilisateur();
		
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerCesAttributs() {
		Utilisateur utilisateur = unUtilisateur();
		
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateur.getAdresse()).isEqualTo("1 rue Test");
		assertThat(utilisateur.getTelephone()).isEqualTo("0607080910");
		assertThat(utilisateur.getDerniereConnexion()).isNull();
		assertThat(utilisateur.isConnecte()).isFalse();
	}
	
	private Utilisateur unUtilisateur() {
		return new FabriqueUtlisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "0607080910");
	}
	
}
