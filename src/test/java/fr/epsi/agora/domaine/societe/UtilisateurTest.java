package fr.epsi.agora.domaine.societe;

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
	public void peutDonnerUnNom() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
	}
	
	@Test
	public void peutDonnerUnPrenom() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
	}
	
	@Test
	public void peutDonnerUnEmail() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
	}
	
	@Test
	public void peutDonnerUnMotDePasse() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
	}
	
	@Test
	public void peutDonnerUneAdresse() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getAdresse()).isEqualTo("1 rue Test");
	}
	
	@Test
	public void peutDonnerUnCodePostal() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getCodePostal()).isEqualTo("33000");
	}
	
	@Test
	public void peutDonnerUnTelephone() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getTelephone()).isEqualTo("0607080910");
	}
	
}
