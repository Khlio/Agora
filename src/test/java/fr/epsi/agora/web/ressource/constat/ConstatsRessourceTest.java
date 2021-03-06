package fr.epsi.agora.web.ressource.constat;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ConstatsRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheConstats.class);
		ressource = new ConstatsRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherTousLesConstatsDunUtilisateur() throws IOException {
		initialiseRessource();
		List<DetailsConstat> constats = Lists.newArrayList();
		DetailsConstat details = new DetailsConstat();
		details.setId(UUID.randomUUID().toString());
		constats.add(details);
		when(recherche.tousDunUtilisateur(idUtilisateur)).thenReturn(constats);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(constats.get(0).getId());
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
	}
	
	private void initialiseRessource() {
		idUtilisateur = UUID.randomUUID();
		Session.ajoute(idUtilisateur.toString());
		RessourceHelper.initialise(ressource).avec("idUtilisateur", idUtilisateur);
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private ConstatsRessource ressource;
	private UUID idUtilisateur;
	
}
