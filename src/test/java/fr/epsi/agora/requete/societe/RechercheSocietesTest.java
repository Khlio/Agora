package fr.epsi.agora.requete.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;

public class RechercheSocietesTest {

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
	public void peutRecupererToutesLesSocietes() {
		jongo.getCollection("societe").insert("{siret: '552-120-222 00013', nom: 'Société générale'}");
		RechercheSocietes recherche = new RechercheSocietes(jongo);
		
		List<ResumeSociete> societes = recherche.toutes();
		
		assertThat(societes).isNotNull();
		assertThat(societes).hasSize(1);
		assertThat(societes.get(0).getSiret()).isEqualTo("552-120-222 00013");
		assertThat(societes.get(0).getNom()).isEqualTo("Société générale");
	}
	
	@Test
	public void peutRecupererUneSociete() {
		UUID id = UUID.randomUUID();
		jongo.getCollection("societe").insert("{_id: #, siret: '552-120-222 00013', nom: 'Société générale', utilisateurs: [#], clients: [#]}",
				id, UUID.randomUUID(), UUID.randomUUID());
		RechercheSocietes recherche = new RechercheSocietes(jongo);
		
		DetailsSociete details = recherche.detailsDe(id);
		
		assertThat(details).isNotNull();
		assertThat(details.getSiret()).isEqualTo("552-120-222 00013");
		assertThat(details.getNom()).isEqualTo("Société générale");
		assertThat(details.getUtilisateurs()).hasSize(1);
		assertThat(details.getClients()).hasSize(1);
	}
	
	@Test
	public void peutRecupererUneSocieteDunUtilisateur() {
		UUID idUtilisateur = UUID.randomUUID();
		jongo.getCollection("societe").insert("{nom: 'test', utilisateurs: [#]}", idUtilisateur);
		RechercheSocietes recherche = new RechercheSocietes(jongo);
		
		DetailsSociete details = recherche.societeDeLUtilisateur(idUtilisateur);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("test");
		assertThat(details.getUtilisateurs()).hasSize(1);
		assertThat(details.getUtilisateurs().get(0)).isEqualTo(idUtilisateur.toString());
	}
	
	@Test
	public void peutRecupererUneSocieteDunClient() {
		UUID idClient = UUID.randomUUID();
		jongo.getCollection("societe").insert("{nom: 'test', clients: [#]}", idClient);
		RechercheSocietes recherche = new RechercheSocietes(jongo);
		
		DetailsSociete details = recherche.societeDuClient(idClient);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("test");
		assertThat(details.getClients()).hasSize(1);
		assertThat(details.getClients().get(0)).isEqualTo(idClient.toString());
	}
	
	@Test
	public void peutSavoirSiUnSiretEstDejaUtilise() {
		jongo.getCollection("societe").insert("{siret: '552-120-222 00013'}");
		RechercheSocietes recherche = new RechercheSocietes(jongo);
		
		boolean siretUtilise = recherche.verifiePresenceSiret("552-120-222 00013");
		
		assertThat(siretUtilise).isTrue();
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
