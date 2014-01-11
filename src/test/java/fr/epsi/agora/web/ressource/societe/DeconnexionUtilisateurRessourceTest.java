package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.CookieSetting;
import org.restlet.data.Status;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.DeconnexionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.RessourceHelper;


public class DeconnexionUtilisateurRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		recherche = mock(RechercheUtilisateurs.class);
		ressource = new DeconnexionUtilisateurRessource(busCommande, recherche);
	}
	
	@Test
	public void peutDeconnecterLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		ressource.getCookieSettings().add(new CookieSetting("utilisateur", details.getId()));
		
		ressource.deconnecte();
		
		assertThat(Session.get(details.getId()).isPresent()).isFalse();
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		ArgumentCaptor<DeconnexionUtilisateurMessage> capteur = ArgumentCaptor.forClass(DeconnexionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		DeconnexionUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
		assertThat(ressource.getCookieSettings().get(0).getMaxAge()).isEqualTo(0);
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
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private DeconnexionUtilisateurRessource ressource;
	
}
