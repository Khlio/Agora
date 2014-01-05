package fr.epsi.agora.web.ressource.utilisateur;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.DeconnexionUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.RessourceHelper;


public class DeconnexionUtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		busCommande = mock(BusCommande.class);
		ressource = new DeconnexionUtilisateurRessource(busCommande, recherche);
	}
	
	@Test
	public void peutDeconnecterLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		ressource.deconnecte();
		
		ArgumentCaptor<DeconnexionUtilisateurMessage> capteur = ArgumentCaptor.forClass(DeconnexionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		DeconnexionUtilisateurMessage commande = capteur.getValue();
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
	private DeconnexionUtilisateurRessource ressource;
	private BusCommande busCommande;
	
}
