package fr.epsi.agora.requete.societe;

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
				+ "telephone: '0607080910'}", id);
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		DetailsUtilisateur details = recherche.detailsDe(id);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Levacher");
		assertThat(details.getPrenom()).isEqualTo("Vincent");
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getMotDePasse()).isEqualTo("pass");
		assertThat(details.getAdresse()).isEqualTo("1 rue Test");
		assertThat(details.getTelephone()).isEqualTo("0607080910");
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
	
	@Test
	public void peutAfficherTousLesUtilisateursDuneSociete() {
		UUID idUtilisateur = UUID.randomUUID();
		DetailsSociete societe = new DetailsSociete();
		societe.getUtilisateurs().add(idUtilisateur.toString());
		jongo.getCollection("utilisateur").insert("{_id: #, nom: 'Levacher'}", idUtilisateur);
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		List<ResumeUtilisateur> utilisateurs = recherche.tousDuneSociete(societe);
		
		assertThat(utilisateurs).isNotNull();
		assertThat(utilisateurs).hasSize(1);
		assertThat(utilisateurs.get(0).getNom()).isEqualTo("Levacher");
	}
	
	@Test
	public void peutSavoirSiUnEmailEstDejaUtilise() {
		jongo.getCollection("utilisateur").insert("{email: 'a@a.com'}");
		RechercheUtilisateurs recherche = new RechercheUtilisateurs(jongo);
		
		boolean emailUtilise = recherche.verifiePresenceEmail("a@a.com");
		
		assertThat(emailUtilise).isTrue();
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
