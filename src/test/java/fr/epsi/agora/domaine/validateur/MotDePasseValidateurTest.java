package fr.epsi.agora.domaine.validateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class MotDePasseValidateurTest {

	@Test
	public void peutValiderUnMotDePasse() {
		String motDePasse = "Azerty1=";
		assertThat(MotDePasseValidateur.valide(motDePasse).aDesErreurs()).isFalse();
		
		motDePasse = "@=bon4joUr123m)";
		assertThat(MotDePasseValidateur.valide(motDePasse).aDesErreurs()).isFalse();
	}
	
	@Test
	public void peutVerifierUnMotDePasseNonValide() {
		String motDePasse = "bouh";
		assertThat(MotDePasseValidateur.valide(motDePasse).aDesErreurs()).isTrue();
		
		motDePasse = "Azerty11";
		assertThat(MotDePasseValidateur.valide(motDePasse).aDesErreurs()).isTrue();
		
		motDePasse = "azerty1=";
		assertThat(MotDePasseValidateur.valide(motDePasse).aDesErreurs()).isTrue();
		
		motDePasse = "@=bonjour123";
		assertThat(MotDePasseValidateur.valide(motDePasse).aDesErreurs()).isTrue();
	}
	
}