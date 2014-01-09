package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ConnexionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.RessourceHelper;
import fr.epsi.agora.web.ressource.societe.ConnexionUtilisateurRessource;

public class ConnexionUtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		busCommande = mock(BusCommande.class);
		ressource = new ConnexionUtilisateurRessource(busCommande, recherche);
	}
	
	@Test
	public void peutConnecterLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		ressource.connecte();
		
		ArgumentCaptor<ConnexionUtilisateurMessage> capteur = ArgumentCaptor.forClass(ConnexionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		ConnexionUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
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
	private ConnexionUtilisateurRessource ressource;
	private BusCommande busCommande;
	
}
