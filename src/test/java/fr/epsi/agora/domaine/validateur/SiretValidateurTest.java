package fr.epsi.agora.domaine.validateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SiretValidateurTest {

	@Test
	public void peutValiderUnSiret() {
		String siret = "123-456-789 12345";
		assertThat(SiretValidateur.valide(siret).aDesErreurs()).isFalse();
		
		siret = "123 456 789 12345";
		assertThat(SiretValidateur.valide(siret).aDesErreurs()).isFalse();
	}
	
	@Test
	public void peutVerifierUnSiretNonValide() {
		String siret = "123456789 12345";
		assertThat(SiretValidateur.valide(siret).aDesErreurs()).isTrue();
		
		siret = "123-456/789 123456";
		assertThat(SiretValidateur.valide(siret).aDesErreurs()).isTrue();
		
		siret = "123-456789  123456";
		assertThat(SiretValidateur.valide(siret).aDesErreurs()).isTrue();
	}
	
}
