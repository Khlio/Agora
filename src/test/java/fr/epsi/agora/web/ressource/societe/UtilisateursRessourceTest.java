package fr.epsi.agora.web.ressource.societe;

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
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class UtilisateursRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheSocietes.class);
		ressource = new UtilisateursRessource(busCommande, recherche);
		RessourceHelper.initialise(ressource);
	}
	
	@Test
	public void peutAfficherTousLesUtilisateursDuneSociete() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		
		List<DetailsUtilisateur> utilisateurs = Lists.newArrayList();
		DetailsUtilisateur detailsUtilisateur = new DetailsUtilisateur();
		detailsUtilisateur.setId(UUID.randomUUID().toString());
		utilisateurs.add(detailsUtilisateur);
		details.setUtilisateurs(utilisateurs);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(utilisateurs.get(0).getId());
	}
	
	private DetailsSociete laRechercheRetourne() {
		DetailsSociete details = new DetailsSociete();
		UUID id = UUID.randomUUID();
		details.setId(id.toString());
		when(recherche.detailsDe(id)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsSociete details) {
		RessourceHelper.initialise(ressource).avec("id", details.getId());
	}
	
	private BusCommande busCommande;
	private UtilisateursRessource ressource;
	private RechercheSocietes recherche;
	
}
