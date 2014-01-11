package fr.epsi.agora.web.ressource.constat;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;

import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ConstatsClientRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheConstats.class);
		ressource = new ConstatsClientRessource(recherche);
	}
	
	@Test
	public void peutAfficherTousLesConstatsDunClient() throws IOException {
		initialiseRessource();
		List<DetailsConstat> constats = Lists.newArrayList();
		DetailsConstat details = new DetailsConstat();
		details.setId(UUID.randomUUID().toString());
		constats.add(details);
		when(recherche.tousDunClient(idClient)).thenReturn(constats);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(constats.get(0).getId());
	}
	
	private void initialiseRessource() {
		idClient = UUID.randomUUID();
		Session.ajoute(idClient.toString());
		UUID idUtilisateur = UUID.randomUUID();
		Session.ajoute(idUtilisateur.toString());
		RessourceHelper.initialise(ressource).avec("idUtilisateur", idUtilisateur, false).avec("idClient", idClient);
	}
	
	private RechercheConstats recherche;
	private ConstatsClientRessource ressource;
	private UUID idClient;
	
}
