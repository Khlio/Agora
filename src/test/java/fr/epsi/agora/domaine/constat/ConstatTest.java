package fr.epsi.agora.domaine.constat;

import static org.fest.assertions.Assertions.assertThat;

import org.joda.time.DateTime;
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
	public void peutDonnerUneAdresse1() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getAdresse1()).isEqualTo("1 rue du Bordel");
	}
	
	@Test
	public void peutDonnerUneAdresse2() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getAdresse2()).isEqualTo("bis");
	}
	
	@Test
	public void peutDonnerUnCodePostal() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getCodePostal()).isEqualTo("87000");
	}
	
	@Test
	public void peutDonnerUneDate() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getDate().getDayOfMonth()).isEqualTo(DateTime.now().getDayOfMonth());
		assertThat(constat.getDate().getMonthOfYear()).isEqualTo(DateTime.now().getMonthOfYear());
		assertThat(constat.getDate().getYear()).isEqualTo(DateTime.now().getYear());
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
	
	@Test
	public void peutDonnerUneListeDeMedias() {
		Constat constat = FakeFabriqueConstat.nouveau();
		
		assertThat(constat.getMedias()).isNotNull();
		assertThat(constat.getMedias()).hasSize(1);
		assertThat(constat.getMedias().get(0)).isEqualTo("\\\\medias\\audio.mp3");
	}
	
}
