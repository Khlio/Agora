package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import fr.epsi.agora.domaine.validateur.Erreur;
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
	public void peutConnecterUnUtilisateur() throws IOException {
		initialiseRessource();
		DetailsUtilisateur details = new DetailsUtilisateur();
		details.setId(UUID.randomUUID().toString());
		details.setEmail("a@a.com");
		details.setMotDePasse("pass");
		when(recherche.detailsDe(details.getEmail(), details.getMotDePasse())).thenReturn(details);
		Form formulaire = new Form();
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "pass");
		
		Representation represente = ressource.connecte(formulaire);
		
		assertThat(Session.get(details.getId()).isPresent()).isTrue();
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(details.getId());
	}
	
	@Test
	public void peutVerifierQueLesIdentifiantsNExistentPas() throws IOException {
		initialiseRessource();
		DetailsUtilisateur details = new DetailsUtilisateur();
		details.setId(UUID.randomUUID().toString());
		details.setEmail("a@a.com");
		details.setMotDePasse("pass");
		when(recherche.detailsDe(details.getEmail(), details.getMotDePasse())).thenReturn(details);
		Form formulaire = new Form();
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "test");
		
		Representation represente = ressource.connecte(formulaire);
		
		assertThat(Session.get(details.getId()).isPresent()).isFalse();
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.IDENTIFIANTS_INTROUVABLE);
	}
	
	@Test
	public void peutVerifierQueLEmailEstInvalide() throws IOException {
		initialiseRessource();
		Form formulaire = new Form();
		formulaire.add("email", "aa.com");
		
		Representation represente = ressource.connecte(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.EMAIL_INVALIDE);
	}
	
	private void initialiseRessource() {
		RessourceHelper.initialise(ressource);
	}
	
	private RechercheUtilisateurs recherche;
	private ConnexionUtilisateurRessource ressource;
	
}
