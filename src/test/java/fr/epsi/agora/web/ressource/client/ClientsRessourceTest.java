package fr.epsi.agora.web.ressource.client;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.requete.client.RechercheClients;
import fr.epsi.agora.requete.client.ResumeClient;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ClientsRessourceTest {

	@Before
	public void setUp() throws Exception {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheClients.class);
		ressource = new ClientsRessource(busCommande, recherche);
		RessourceHelper.initialise(ressource);
	}
	
	@Test
	public void peutAfficherTousLesUtilisateurs() throws IOException {
		List<ResumeClient> clients = Lists.newArrayList();
		ResumeClient resume = new ResumeClient();
		resume.setId(UUID.randomUUID().toString());
		clients.add(resume);
		when(recherche.tous()).thenReturn(clients);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getText()).contains(clients.get(0).getId());
	}
	
	private BusCommande busCommande;
	private ClientsRessource ressource;
	private RechercheClients recherche;
	
}
