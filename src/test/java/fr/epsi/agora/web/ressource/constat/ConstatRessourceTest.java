package fr.epsi.agora.web.ressource.constat;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.ModificationConstatMessage;
import fr.epsi.agora.commande.constat.SuppressionConstatMessage;
import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ConstatRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		recherche = mock(RechercheConstats.class);
		ressource = new ConstatRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherLeConstat() throws IOException {
		DetailsConstat details = laRechercheRetourne();
		initialiseRessource(details);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(details.getId());
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
	}
	
	@Test
	public void peutModifierLeConstat() throws IOException {
		DetailsConstat details = laRechercheRetourne();
		details.setNom("Tout cassé");
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("nom", details.getNom());
		formulaire.add("adresse1", "test");
		
		Representation represente = ressource.modifie(formulaire);
		
		ArgumentCaptor<ModificationConstatMessage> capteur = ArgumentCaptor.forClass(ModificationConstatMessage.class);
		verify(busCommande).envoie(capteur.capture());
		ModificationConstatMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.nom).isEqualTo(details.getNom());
		assertThat(commande.adresse1).isEqualTo("test");
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
	}
	
	@Test
	public void peutSupprimerLeConstat() throws IOException {
		DetailsConstat details = laRechercheRetourne();
		initialiseRessource(details);
		
		Representation represente = ressource.supprime();
		
		ArgumentCaptor<SuppressionConstatMessage> capteur = ArgumentCaptor.forClass(SuppressionConstatMessage.class);
		verify(busCommande).envoie(capteur.capture());
		SuppressionConstatMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
	}
	
	private DetailsConstat laRechercheRetourne() {
		DetailsConstat details = new DetailsConstat();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsConstat details) {
		UUID idUtilisateur = UUID.randomUUID();
		Session.ajoute(idUtilisateur.toString());
		RessourceHelper.initialise(ressource).avec("idConstat", details.getId(), false).avec("idUtilisateur", idUtilisateur);
	}
	
	private BusCommande busCommande;
	private RechercheConstats recherche;
	private ConstatRessource ressource;
	
}
