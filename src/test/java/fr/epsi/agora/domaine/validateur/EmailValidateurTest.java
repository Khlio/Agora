package fr.epsi.agora.domaine.validateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class EmailValidateurTest {

	@Test
	public void peutValiderUnEmail() {
		String email = "a_535@a.com";
		
		assertThat(EmailValidateur.valide(email).aDesErreurs()).isFalse();
	}
	
	@Test
	public void peutVerifierUnEmailNonValide() {
		String email = "a@a.";
		assertThat(EmailValidateur.valide(email).aDesErreurs()).isTrue();
		
		email = "A@a.com";
		assertThat(EmailValidateur.valide(email).aDesErreurs()).isTrue();
		
		email = "aaa.fr";
		assertThat(EmailValidateur.valide(email).aDesErreurs()).isTrue();
	}
	
}
