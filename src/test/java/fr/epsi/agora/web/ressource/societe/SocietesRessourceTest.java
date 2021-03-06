package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.commande.societe.CreationSocieteMessage;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.ResumeSociete;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class SocietesRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheSocietes.class);
		ressource = new SocietesRessource(busCommande, recherche);
		RessourceHelper.initialise(ressource);
	}
	
	@Test
	public void peutAfficherToutesLesSocietes() throws IOException {
		List<ResumeSociete> societes = Lists.newArrayList();
		ResumeSociete resume = new ResumeSociete();
		resume.setId(UUID.randomUUID().toString());
		societes.add(resume);
		when(recherche.toutes()).thenReturn(societes);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(societes.get(0).getId());
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
	}
	
	@Test
	public void peutCreerUneSociete() throws IOException {
		Form formulaire = new Form();
		formulaire.add("siret", "552-120-222 00013");
		formulaire.add("nom", "Société générale");
		
		Representation represente = ressource.cree(formulaire);
		
		ArgumentCaptor<CreationSocieteMessage> capteur = ArgumentCaptor.forClass(CreationSocieteMessage.class);
		verify(busCommande).envoie(capteur.capture());
		CreationSocieteMessage commande = capteur.getValue();
		assertThat(commande.siret).isEqualTo("552-120-222 00013");
		assertThat(commande.nom).isEqualTo("Société générale");
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isNotEmpty();
	}
	
	@Test
	public void peutVerifierQueLeSiretEstInvalide() throws IOException {
		Form formulaire = new Form();
		formulaire.add("siret", "552-120-22200013");
		
		Representation represente = ressource.cree(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.SIRET_INVALIDE);
	}
	
	@Test
	public void peutVerifierQueLeSiretEstUtilise() throws IOException {
		Form formulaire = new Form();
		formulaire.add("siret", "552-120-222 00013");
		when(recherche.verifiePresenceSiret("552-120-222 00013")).thenReturn(true);
		
		Representation represente = ressource.cree(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.SIRET_EXISTANT);
	}
	
	private BusCommande busCommande;
	private RechercheSocietes recherche;
	private SocietesRessource ressource;
	
}
