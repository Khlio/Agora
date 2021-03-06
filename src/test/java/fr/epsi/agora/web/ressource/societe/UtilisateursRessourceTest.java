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
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.requete.societe.ResumeUtilisateur;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class UtilisateursRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheUtilisateurs.class);
		rechercheSocietes = mock(RechercheSocietes.class);
		ressource = new UtilisateursRessource(recherche, rechercheSocietes);
	}
	
	@Test
	public void peutAfficherTousLesUtilisateursDuneSociete() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		
		List<ResumeUtilisateur> utilisateurs = Lists.newArrayList();
		ResumeUtilisateur resume = new ResumeUtilisateur();
		resume.setId(UUID.randomUUID().toString());
		utilisateurs.add(resume);
		when(recherche.tousDuneSociete(details)).thenReturn(utilisateurs);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(utilisateurs.get(0).getId());
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
	}
	
	private DetailsSociete laRechercheRetourne() {
		DetailsSociete details = new DetailsSociete();
		UUID id = UUID.randomUUID();
		details.setId(id.toString());
		when(rechercheSocietes.detailsDe(id)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsSociete details) {
		RessourceHelper.initialise(ressource).avec("id", details.getId());
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private RechercheSocietes rechercheSocietes;
	private UtilisateursRessource ressource;
	
}
