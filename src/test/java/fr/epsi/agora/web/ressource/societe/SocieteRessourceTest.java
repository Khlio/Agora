package fr.epsi.agora.web.ressource.societe;

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
import fr.epsi.agora.commande.societe.AjoutUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionSocieteMessage;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class SocieteRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		recherche = mock(RechercheSocietes.class);
		ressource = new SocieteRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherLaSociete() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(details.getId());
	}
	
	@Test
	public void peutAjouterUnUtilisateur() {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("nom", "Levacher");
		formulaire.add("prenom", "Vincent");
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "pass");
		formulaire.add("adresse", "1 rue Test");
		formulaire.add("telephone", "0607080910");
		
		ressource.ajouteUtilisateur(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		ArgumentCaptor<AjoutUtilisateurMessage> capteur = ArgumentCaptor.forClass(AjoutUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		AjoutUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.idSociete).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.nom).isEqualTo("Levacher");
		assertThat(commande.prenom).isEqualTo("Vincent");
		assertThat(commande.email).isEqualTo("a@a.com");
		assertThat(commande.motDePasse).isEqualTo("pass");
		assertThat(commande.adresse).isEqualTo("1 rue Test");
		assertThat(commande.telephone).isEqualTo("0607080910");
	}
	
	@Test
	public void peutSupprimerLaSociete() {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		
		ressource.supprime();
		
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		ArgumentCaptor<SuppressionSocieteMessage> capteur = ArgumentCaptor.forClass(SuppressionSocieteMessage.class);
		verify(busCommande).envoie(capteur.capture());
		SuppressionSocieteMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
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
	private RechercheSocietes recherche;
	private SocieteRessource ressource;
	
}
