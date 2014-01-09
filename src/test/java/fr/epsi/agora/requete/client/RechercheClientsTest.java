package fr.epsi.agora.requete.client;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;
import java.util.List;
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
	public void peutRecupererTousLesClients() {
		jongo.getCollection("client").insert("{nom: 'Levacher', prenom: 'Vincent'}");
		RechercheClients recherche = new RechercheClients(jongo);
		
		List<ResumeClient> clients = recherche.tous();
		
		assertThat(clients).isNotNull();
		assertThat(clients).hasSize(1);
		ResumeClient resume = clients.get(0);
		assertThat(resume.getNom()).isEqualTo("Levacher");
		assertThat(resume.getPrenom()).isEqualTo("Vincent");
	}
	
	@Test
	public void peutRecupererUnClient() {
		UUID idClient = UUID.randomUUID();
		UUID idSociete = UUID.randomUUID();
		UUID idUtilisateur = UUID.randomUUID();
		jongo.getCollection("client").insert("{_id: #, nom: 'Levacher', prenom: 'Vincent', email: 'a@a.com', dateDeNaissance: #, lieuDeNaissance: 'Limoges', metier: 'Développeur', "
				+ "nationalite: 'Française', adresse: '1 rue Test', telephone: '0607080910', "
				+ "societe: {_id: #, siret: '552-120-222 00013', nom: 'Société générale'}, utilisateurs: [{_id: #, nom: 'Saban', prenom: 'JR'}]}", idClient, new Date(), idSociete, idUtilisateur);
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
		assertThat(details.getSociete()).isNotNull();
		assertThat(details.getUtilisateurs()).hasSize(1);
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
