package fr.epsi.agora.requete.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;

public class RechercheUtilisateursTest {

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
	public void peutRecupererTousLesUtilisateurs() {
		jongo.getCollection("utilisateur").insert(
				"{nom: 'Levacher', prenom: 'Vincent', email: 'a@a.com', motDePasse: 'pass', adresse: '1 rue Test', telephone: '0607080910'}");
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		List<DetailsUtilisateur> utilisateurs = recherche.tous();
		
		assertThat(utilisateurs).isNotNull();
		assertThat(utilisateurs).hasSize(1);
		DetailsUtilisateur details = utilisateurs.get(0);
		assertThat(details.getNom()).isEqualTo("Levacher");
		assertThat(details.getPrenom()).isEqualTo("Vincent");
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getMotDePasse()).isEqualTo("pass");
		assertThat(details.getAdresse()).isEqualTo("1 rue Test");
		assertThat(details.getTelephone()).isEqualTo("0607080910");
		assertThat(details.getDerniereConnexion()).isNull();
		assertThat(details.isConnecte()).isNull();
	}
	
	@Test
	public void peutRecupererUnUtilisateur() {
		UUID idUtilisateur = UUID.randomUUID();
		UUID idClient = UUID.randomUUID();
		jongo.getCollection("utilisateur").insert(
				"{_id: #, nom: 'Levacher', prenom: 'Vincent', email: 'a@a.com', motDePasse: 'pass', adresse: '1 rue Test', telephone: '0607080910', connecte: false, "
				+ "clients: [{_id: #, nom: 'Saban', prenom: 'JR'}]}", idUtilisateur, idClient);
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		DetailsUtilisateur details = recherche.detailsDe(idUtilisateur);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Levacher");
		assertThat(details.getPrenom()).isEqualTo("Vincent");
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getMotDePasse()).isEqualTo("pass");
		assertThat(details.getAdresse()).isEqualTo("1 rue Test");
		assertThat(details.getTelephone()).isEqualTo("0607080910");
		assertThat(details.getDerniereConnexion()).isNull();
		assertThat(details.isConnecte()).isFalse();
		assertThat(details.getClients()).hasSize(1);
	}
	
	private Jongo jongo;
	private Fongo fongo;
	
}
