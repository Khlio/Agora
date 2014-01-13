package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.CookieSetting;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import fr.epsi.agora.Constante;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;
import fr.epsi.agora.web.ressource.RessourceHelper;


public class DeconnexionUtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		ressource = new DeconnexionUtilisateurRessource(recherche);
	}
	
	@Test
	public void peutDeconnecterLUtilisateur() throws IOException {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		ressource.getCookieSettings().add(new CookieSetting(Constante.SESSION_COOKIE, details.getId()));
		
		Representation represente = ressource.deconnecte();
		
		assertThat(Session.get(details.getId()).isPresent()).isFalse();
		assertThat(ressource.getCookieSettings().get(0).getMaxAge()).isEqualTo(0);
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
	}
	
	private DetailsUtilisateur laRechercheRetourne() {
		DetailsUtilisateur details = new DetailsUtilisateur();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsUtilisateur details) {
		RessourceHelper.initialise(ressource).avec("id", details.getId());
	}
	
	private RechercheUtilisateurs recherche;
	private DeconnexionUtilisateurRessource ressource;
	
}
