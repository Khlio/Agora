package fr.epsi.agora.requete.constat;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;

public class RechercheConstatsTest {

	@Before
	public void setUp() {
		fongo = new Fongo("test");
		jongo = new Jongo(fongo.getDB("agora"));
	}
	
	@After
	public void tearDown() {
		fongo.dropDatabase("agora");
	}
	
	@Test
	public void peutRecupererUnConstat() {
		UUID idConstat = UUID.randomUUID();
		jongo.getCollection("constat").insert("{_id: #, nom: 'Tout cassé', date: '01/01/2014', geolocalisation: '', "
				+ "utilisateur: {nom: 'Levacher', prenom: 'Vincent'}, client: {nom: 'Saban', prenom: 'JR'}}", idConstat);
		RechercheConstats recherche = new RechercheConstats(jongo);
		
		DetailsConstat details = recherche.detailsDe(idConstat);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Tout cassé");
		assertThat(details.getDate()).isEqualTo("01/01/2014");
		assertThat(details.getGeolocalisation()).isEqualTo("");
		assertThat(details.getUtilisateur()).isNotNull();
		assertThat(details.getUtilisateur().getNom()).isEqualTo("Levacher");
		assertThat(details.getUtilisateur().getPrenom()).isEqualTo("Vincent");
		assertThat(details.getClient()).isNotNull();
		assertThat(details.getClient().getNom()).isEqualTo("Saban");
		assertThat(details.getClient().getPrenom()).isEqualTo("JR");
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
