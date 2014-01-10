package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;
import org.restlet.data.Status;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ConnexionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ConnexionUtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		busCommande = mock(BusCommande.class);
		ressource = new ConnexionUtilisateurRessource(busCommande, recherche);
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
		
		assertThat(ressource.getResponse().getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		ArgumentCaptor<ConnexionUtilisateurMessage> capteur = ArgumentCaptor.forClass(ConnexionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		ConnexionUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
	}
	
	private void initialiseRessource() {
		RessourceHelper.initialise(ressource);
	}
	
	private RechercheUtilisateurs recherche;
	private ConnexionUtilisateurRessource ressource;
	private BusCommande busCommande;
	
}
