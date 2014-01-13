package fr.epsi.agora.domaine.validateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class TelephoneValidateurTest {

	@Test
	public void peutValiderUnNumeroDeTelephone() {
		String telephone = "07 08 09 10 06";
		assertThat(TelephoneValidateur.valide(telephone).aDesErreurs()).isFalse();
		
		telephone = "09.08.09.10.06";
		assertThat(TelephoneValidateur.valide(telephone).aDesErreurs()).isFalse();
		
		telephone = "01-08-09-10-06";
		assertThat(TelephoneValidateur.valide(telephone).aDesErreurs()).isFalse();
		
		telephone = "0508091006";
		assertThat(TelephoneValidateur.valide(telephone).aDesErreurs()).isFalse();
	}
	
	@Test
	public void peutVerifierUnNumeroDeTelephoneNonValide() {
		String telephone = "06070809100";
		assertThat(TelephoneValidateur.valide(telephone).aDesErreurs()).isTrue();
		
		telephone = "0001020304";
		assertThat(TelephoneValidateur.valide(telephone).aDesErreurs()).isTrue();
	}
	
}