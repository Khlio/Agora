package fr.epsi.agora.domaine.constat;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ConstatTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat).isNotNull();
		assertThat(constat.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerUnNom() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getNom()).isEqualTo("Tout cassé");
	}
	
	@Test
	public void peutDonnerUneDate() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getDate()).isEqualTo("01/01/2014");
	}
	
	@Test
	public void peutDonnerUneGeolocalisation() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getGeolocalisation()).isEqualTo("");
	}
	
	@Test
	public void peutDonnerUnUtilisateur() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getUtilisateur()).isNotNull();
	}
	
	@Test
	public void peutDonnerUnClient() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getClient()).isNotNull();
	}
	
}
