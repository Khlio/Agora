package fr.epsi.agora.web.ressource.client;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.requete.client.DetailsClient;
import fr.epsi.agora.requete.client.RechercheClients;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ClientRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheClients.class);
		busCommande = mock(BusCommande.class);
		ressource = new ClientRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherLeClient() throws IOException {
		DetailsClient details = laRechercheRetourne();
		initialiserRessource(details);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(details.getId());
	}
	
	private DetailsClient laRechercheRetourne() {
		DetailsClient details = new DetailsClient();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiserRessource(DetailsClient details) {
		RessourceHelper.initialise(ressource).avec("id", details.getId());
	}
	
	private RechercheClients recherche;
	private BusCommande busCommande;
	private ClientRessource ressource;
	
}
