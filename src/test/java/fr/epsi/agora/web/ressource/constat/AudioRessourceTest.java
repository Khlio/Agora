package fr.epsi.agora.web.ressource.constat;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class AudioRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(null);
		ressource = new AudioRessource(busCommande);
	}
	
	@Test
	public void peutSupprimerUnFichierAudio() throws IOException {
		UUID idConstat = laRechercheRetourne();
		initialiseRessource(idConstat);
		when(busCommande.envoie(any(Message.class))).thenReturn(null);
		
		Representation represente = ressource.supprimeFichier(new StringRepresentation(""));
		
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
	}
	
	private UUID laRechercheRetourne() {
		return UUID.randomUUID();
	}
	
	private void initialiseRessource(UUID idConstat) {
		UUID idUtilisateur = UUID.randomUUID();
		Session.ajoute(idUtilisateur.toString());
		RessourceHelper.initialise(ressource).avec("idConstat", idConstat.toString(), false).avec("idUtilisateur", idUtilisateur);
	}
	
	private BusCommande busCommande;
	private AudioRessource ressource;
	
}
