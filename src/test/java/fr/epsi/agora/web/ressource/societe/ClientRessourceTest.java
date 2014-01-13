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
import fr.epsi.agora.commande.societe.ModificationClientMessage;
import fr.epsi.agora.commande.societe.SuppressionClientMessage;
import fr.epsi.agora.requete.societe.DetailsClient;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ClientRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		recherche = mock(RechercheClients.class);
		rechercheSocietes = mock(RechercheSocietes.class);
		ressource = new ClientRessource(busCommande, recherche, rechercheSocietes);
	}
	
	@Test
	public void peutAfficherLeClient() throws IOException {
		DetailsClient details = laRechercheRetourne();
		initialiseRessource(details);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(details.getId());
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
	}
	
	@Test
	public void peutModifierLeClient() throws IOException {
		DetailsClient details = laRechercheRetourne();
		details.setNom("Saban");
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("nom", details.getNom());
		formulaire.add("email", "b@b.com");
		formulaire.add("telephonePortable", "0607080910");
		
		Representation represente = ressource.modifie(formulaire);
		
		ArgumentCaptor<ModificationClientMessage> capteur = ArgumentCaptor.forClass(ModificationClientMessage.class);
		verify(busCommande).envoie(capteur.capture());
		ModificationClientMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.nom).isEqualTo(details.getNom());
		assertThat(commande.email).isEqualTo("b@b.com");
		assertThat(commande.telephonePortable).isEqualTo("0607080910");
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
	}
	
	@Test
	public void peutSupprimerLeClient() throws IOException {
		DetailsClient details = laRechercheRetourne();
		initialiseRessource(details);
		DetailsSociete societe = new DetailsSociete();
		societe.setId(UUID.randomUUID().toString());
		when(rechercheSocietes.societeDuClient(UUID.fromString(details.getId()))).thenReturn(societe);
		
		Representation represente = ressource.supprime();
		
		ArgumentCaptor<SuppressionClientMessage> capteur = ArgumentCaptor.forClass(SuppressionClientMessage.class);
		verify(busCommande).envoie(capteur.capture());
		SuppressionClientMessage commande = capteur.getValue();
		assertThat(commande.idClient).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.idSociete).isEqualTo(UUID.fromString(societe.getId()));
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
	}
	
	private DetailsClient laRechercheRetourne() {
		DetailsClient details = new DetailsClient();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsClient details) {
		UUID idUtilisateur = UUID.randomUUID();
		Session.ajoute(idUtilisateur.toString());
		RessourceHelper.initialise(ressource).avec("idClient", details.getId(), false).avec("idUtilisateur", idUtilisateur);
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;
	private RechercheSocietes rechercheSocietes;
	private ClientRessource ressource;
	
}
