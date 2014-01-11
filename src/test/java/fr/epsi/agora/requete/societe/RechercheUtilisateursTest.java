package fr.epsi.agora.requete.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;

public class RechercheUtilisateursTest {

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
	public void peutRecupererUnUtilisateur() {
		UUID id = UUID.randomUUID();
		jongo.getCollection("utilisateur").insert("{_id: #, nom: 'Levacher', prenom: 'Vincent', email: 'a@a.com', motDePasse: 'pass', adresse: '1 rue Test', "
				+ "telephone: '0607080910', connecte: false}", id);
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		DetailsUtilisateur details = recherche.detailsDe(id);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Levacher");
		assertThat(details.getPrenom()).isEqualTo("Vincent");
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getMotDePasse()).isEqualTo("pass");
		assertThat(details.getAdresse()).isEqualTo("1 rue Test");
		assertThat(details.getTelephone()).isEqualTo("0607080910");
		assertThat(details.getDerniereConnexion()).isNull();
		assertThat(details.isConnecte()).isFalse();
	}
	
	@Test
	public void peutRecupererUnUtilisateurSelonSesIdentifiants() {
		jongo.getCollection("utilisateur").insert("{_id: #, email: 'a@a.com', motDePasse: 'pass'}", UUID.randomUUID());
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		DetailsUtilisateur details = recherche.detailsDe("a@a.com", "pass");
		
		assertThat(details).isNotNull();
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getMotDePasse()).isEqualTo("pass");
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
