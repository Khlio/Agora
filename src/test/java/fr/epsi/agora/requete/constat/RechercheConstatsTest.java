package fr.epsi.agora.requete.constat;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
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
	public void peutRecupererTousLesConstatsDunUtilisateur() {
		UUID idUtilisateur = UUID.randomUUID();
		jongo.getCollection("constat").insert("{nom: 'Tout cassé', adresse1: '1 rue du Bordel', codePostal: '87000', date: #, geolocalisation: '', "
				+ "utilisateur: #}", DateTime.now().getMillis(), idUtilisateur);
		jongo.getCollection("constat").insert("{nom: 'test'}");
		RechercheConstats recherche = new RechercheConstats(jongo);
		
		List<DetailsConstat> constats = recherche.tousDunUtilisateur(idUtilisateur);
		
		assertThat(constats).isNotNull();
		assertThat(constats).hasSize(1);
		DetailsConstat constat = constats.get(0);
		assertThat(constat.getNom()).isEqualTo("Tout cassé");
		assertThat(constat.getAdresse1()).isEqualTo("1 rue du Bordel");
		assertThat(constat.getAdresse2()).isNull();
		assertThat(constat.getCodePostal()).isEqualTo("87000");
		assertThat(constat.getDate()).isNotNull();
		assertThat(constat.getGeolocalisation()).isEqualTo("");
		assertThat(constat.getUtilisateur()).isEqualTo(idUtilisateur.toString());
	}
	
	@Test
	public void peutRecupererTousLesConstatsDunClient() {
		UUID idClient = UUID.randomUUID();
		jongo.getCollection("constat").insert("{nom: 'Tout cassé', adresse1: '1 rue du Bordel', codePostal: '87000', date: #, geolocalisation: '', "
				+ "client: #}", DateTime.now().getMillis(), idClient);
		jongo.getCollection("constat").insert("{nom: 'test'}");
		RechercheConstats recherche = new RechercheConstats(jongo);
		
		List<DetailsConstat> constats = recherche.tousDunClient(idClient);
		
		assertThat(constats).isNotNull();
		assertThat(constats).hasSize(1);
		DetailsConstat constat = constats.get(0);
		assertThat(constat.getNom()).isEqualTo("Tout cassé");
		assertThat(constat.getAdresse1()).isEqualTo("1 rue du Bordel");
		assertThat(constat.getAdresse2()).isNull();
		assertThat(constat.getCodePostal()).isEqualTo("87000");
		assertThat(constat.getDate()).isNotNull();
		assertThat(constat.getGeolocalisation()).isEqualTo("");
		assertThat(constat.getClient()).isEqualTo(idClient.toString());
	}
	
	@Test
	public void peutRecupererUnConstat() {
		UUID idConstat = UUID.randomUUID();
		UUID idUtilisateur = UUID.randomUUID();
		UUID idClient = UUID.randomUUID();
		jongo.getCollection("constat").insert("{_id: #, nom: 'Tout cassé', adresse1: '1 rue du Bordel', adresse2: 'bis', codePostal: '87000', date: #, geolocalisation: '', "
				+ "utilisateur: #, client: #, audios: ['\\\\medias\\audio.mp3'], annexes: ['\\\\annexes\\photo.jpg']}", idConstat, DateTime.now().getMillis(), idUtilisateur, idClient);
		RechercheConstats recherche = new RechercheConstats(jongo);
		
		DetailsConstat details = recherche.detailsDe(idConstat);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Tout cassé");
		assertThat(details.getAdresse1()).isEqualTo("1 rue du Bordel");
		assertThat(details.getAdresse2()).isEqualTo("bis");
		assertThat(details.getCodePostal()).isEqualTo("87000");
		String jour = "" + (10 > DateTime.now().getDayOfMonth() ? "0" + DateTime.now().getDayOfMonth() : DateTime.now().getDayOfMonth());
		String mois = "" + (10 > DateTime.now().getMonthOfYear() ? "0" + DateTime.now().getMonthOfYear() : DateTime.now().getMonthOfYear());
		assertThat(details.getDate()).isEqualTo(jour + "-" + mois + "-" + DateTime.now().getYear());
		assertThat(details.getGeolocalisation()).isEqualTo("");
		assertThat(details.getUtilisateur()).isEqualTo(idUtilisateur.toString());
		assertThat(details.getClient()).isEqualTo(idClient.toString());
		assertThat(details.getAudios()).hasSize(1);
		assertThat(details.getAnnexes()).hasSize(1);
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
