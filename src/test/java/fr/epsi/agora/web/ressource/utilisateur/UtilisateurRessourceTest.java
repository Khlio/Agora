package fr.epsi.agora.web.ressource.utilisateur;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.SuppressionUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class UtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		busCommande = mock(BusCommande.class);
		ressource = new UtilisateurRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherLUtilisateur() throws IOException {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(details.getId());
	}
	
	@Test
	public void peutSupprimerLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		ressource.supprime();
		
		ArgumentCaptor<SuppressionUtilisateurMessage> capteur = ArgumentCaptor.forClass(SuppressionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		SuppressionUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.idUtilisateur).isEqualTo(UUID.fromString(details.getId()));
	}
	
	private DetailsUtilisateur laRechercheRetourne() {
		DetailsUtilisateur details = new DetailsUtilisateur();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsUtilisateur detailsUtilisateur) {
		RessourceHelper.initialise(ressource).avec("id", detailsUtilisateur.getId());
	}
	
	private RechercheUtilisateurs recherche;
	private UtilisateurRessource ressource;
	private BusCommande busCommande;
	
}
