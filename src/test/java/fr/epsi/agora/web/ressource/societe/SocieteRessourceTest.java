package fr.epsi.agora.web.ressource.societe;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
import fr.epsi.agora.commande.constat.SuppressionConstatMessage;
import fr.epsi.agora.commande.societe.AjoutUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionClientMessage;
import fr.epsi.agora.commande.societe.SuppressionSocieteMessage;
import fr.epsi.agora.commande.societe.SuppressionUtilisateurMessage;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.requete.societe.ResumeClient;
import fr.epsi.agora.requete.societe.ResumeUtilisateur;
import fr.epsi.agora.web.ressource.ReponseRessource;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class SocieteRessourceTest {

	@Before
	public void setUp() {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheSocietes.class);
		rechercheUtilisateurs = mock(RechercheUtilisateurs.class);
		rechercheClients = mock(RechercheClients.class);
		rechercheConstats = mock(RechercheConstats.class);
        ressource = new SocieteRessource(busCommande, recherche, rechercheUtilisateurs, rechercheClients, rechercheConstats);
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
	public void peutAjouterUnUtilisateur() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("nom", "Levacher");
		formulaire.add("prenom", "Vincent");
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "Azerty1=");
		formulaire.add("adresse", "1 rue Test");
		formulaire.add("telephone", "0607080910");
		
		Representation represente = ressource.ajouteUtilisateur(formulaire);
		
		ArgumentCaptor<AjoutUtilisateurMessage> capteur = ArgumentCaptor.forClass(AjoutUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		AjoutUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.idSociete).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.nom).isEqualTo("Levacher");
		assertThat(commande.prenom).isEqualTo("Vincent");
		assertThat(commande.email).isEqualTo("a@a.com");
		assertThat(commande.motDePasse).isEqualTo("Azerty1=");
		assertThat(commande.adresse).isEqualTo("1 rue Test");
		assertThat(commande.telephone).isEqualTo("0607080910");
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isNotEmpty();
	}
	
	@Test
	public void peutVerifierQueLEmailEstInvalide() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("email", "aa.com");
		
		Representation represente = ressource.ajouteUtilisateur(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.EMAIL_INVALIDE);
	}
	
	@Test
	public void peutVerifierQueLeMotDePasseEstInvalide() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "Azerty1");
		
		Representation represente = ressource.ajouteUtilisateur(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.MOT_DE_PASSE_INVALIDE);
	}
	
	@Test
	public void peutVerifierQueLeTelephoneEstInvalide() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "Azerty1=");
		formulaire.add("telephone", "0007080910");
		
		Representation represente = ressource.ajouteUtilisateur(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.CLIENT_ERROR_BAD_REQUEST);
		assertThat(represente.getText()).isEqualTo(Erreur.TELEPHONE_INVALIDE);
	}
	
	@Test
	public void peutSupprimerLaSociete() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		
		List<ResumeUtilisateur> utilisateurs = Lists.newArrayList();
		ResumeUtilisateur utilisateur = new ResumeUtilisateur();
		utilisateur.setId(UUID.randomUUID().toString());
		utilisateurs.add(utilisateur);
		when(rechercheUtilisateurs.tousDuneSociete(details)).thenReturn(utilisateurs);
		
		List<ResumeClient> clients = Lists.newArrayList();
		ResumeClient client = new ResumeClient();
		client.setId(UUID.randomUUID().toString());
		clients.add(client);
		when(rechercheClients.tousDuneSociete(details)).thenReturn(clients);
		
		List<DetailsConstat> constats = Lists.newArrayList();
		DetailsConstat constat = new DetailsConstat();
		constat.setId(UUID.randomUUID().toString());
		constats.add(constat);
		when(rechercheConstats.tousDunClient(UUID.fromString(client.getId()))).thenReturn(constats);
		
		Representation represente = ressource.supprime();
		
		ArgumentCaptor<Message> capteur = ArgumentCaptor.forClass(Message.class);
		verify(busCommande, times(4)).envoie(capteur.capture());
		
		SuppressionSocieteMessage commande = null;
		SuppressionUtilisateurMessage commandeUtilisateur = null;
		SuppressionClientMessage commandeClient = null;
		SuppressionConstatMessage commandeConstat = null;
		for (Message message : capteur.getAllValues()) {
			if (message instanceof SuppressionSocieteMessage) {
				commande = (SuppressionSocieteMessage) message;
			} else if (message instanceof SuppressionUtilisateurMessage) {
				commandeUtilisateur = (SuppressionUtilisateurMessage) message;
			} else if (message instanceof SuppressionClientMessage) {
				commandeClient = (SuppressionClientMessage) message;
			} else if (message instanceof SuppressionConstatMessage) {
				commandeConstat = (SuppressionConstatMessage) message;
			}
		}
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commandeUtilisateur.idUtilisateur).isEqualTo(UUID.fromString(utilisateur.getId()));
		assertThat(commandeUtilisateur.idSociete).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commandeClient.idClient).isEqualTo(UUID.fromString(client.getId()));
		assertThat(commandeClient.idSociete).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commandeConstat.id).isEqualTo(UUID.fromString(constat.getId()));
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		assertThat(represente.getText()).isEqualTo(ReponseRessource.OK.toString());
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
	private RechercheUtilisateurs rechercheUtilisateurs;
	private RechercheClients rechercheClients;
	private RechercheConstats rechercheConstats;
	private SocieteRessource ressource;
	
}
