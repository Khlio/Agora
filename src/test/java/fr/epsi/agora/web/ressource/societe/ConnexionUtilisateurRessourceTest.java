package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.Status;

import fr.epsi.agora.Constante;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ConnexionUtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		ressource = new ConnexionUtilisateurRessource(recherche);
	}
	
	@Test
	public void peutConnecterUnUtilisateur() {
		initialiseRessource();
		DetailsUtilisateur details = new DetailsUtilisateur();
		details.setId(UUID.randomUUID().toString());
		details.setEmail("a@a.com");
		details.setMotDePasse("pass");
		when(recherche.detailsDe(details.getEmail(), details.getMotDePasse())).thenReturn(details);
		Form formulaire = new Form();
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "pass");
		
		ressource.connecte(formulaire);
		
		assertThat(Session.get(details.getId()).isPresent()).isTrue();
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		
		assertThat(ressource.getCookieSettings()).hasSize(1);
		CookieSetting cookie = ressource.getCookieSettings().get(0);
		assertThat(cookie).isNotNull();
		assertThat(cookie.getName()).isEqualTo(Constante.SESSION_COOKIE);
		assertThat(cookie.getValue()).isEqualTo(details.getId());
		assertThat(cookie.getMaxAge()).isEqualTo(-1);
	}
	
	private void initialiseRessource() {
		RessourceHelper.initialise(ressource);
	}
	
	private RechercheUtilisateurs recherche;
	private ConnexionUtilisateurRessource ressource;
	
}
