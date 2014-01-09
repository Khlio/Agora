package fr.epsi.agora.requete.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;
import java.util.UUID;

import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;

public class RechercheClientsTest {

	@Before
	public void setUp() throws Exception {
		fongo = new Fongo("test");
		jongo = new Jongo(fongo.getDB("agora"));
	}
	
	@After
	public void tearDown() throws Exception {
		fongo.dropDatabase("agora");
	}
	
	@Test
	public void peutRecupererUnClient() {
		UUID idClient = UUID.randomUUID();
		jongo.getCollection("client").insert("{_id: #, nom: 'Levacher', prenom: 'Vincent', email: 'a@a.com', dateDeNaissance: #, lieuDeNaissance: 'Limoges', metier: 'Développeur', "
				+ "nationalite: 'Française', adresse: '1 rue Test', telephone: '0607080910', "
				+ "utilisateurs: [{nom: 'Saban', prenom: 'JR'}]}", idClient, new Date());
		RechercheClients recherche = new RechercheClients(jongo);
		
		DetailsClient details = recherche.detailsDe(idClient);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Levacher");
		assertThat(details.getPrenom()).isEqualTo("Vincent");
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getDateDeNaissance()).isNotNull();
		assertThat(details.getLieuDeNaissance()).isEqualTo("Limoges");
		assertThat(details.getMetier()).isEqualTo("Développeur");
		assertThat(details.getNationalite()).isEqualTo("Française");
		assertThat(details.getAdresse()).isEqualTo("1 rue Test");
		assertThat(details.getTelephone()).isEqualTo("0607080910");
		assertThat(details.getUtilisateurs()).hasSize(1);
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
