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
import fr.epsi.agora.commande.societe.AjoutClientMessage;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.ResumeClient;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class ClientsRessourceTest {

	@Before
	public void setUp() throws Exception {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheClients.class);
		rechercheSocietes = mock(RechercheSocietes.class);
		ressource = new ClientsRessource(busCommande, recherche, rechercheSocietes);
	}
	
	@Test
	public void peutAfficherTousLesClientsDuneSociete() throws IOException {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		
		List<ResumeClient> clients = Lists.newArrayList();
		ResumeClient resume = new ResumeClient();
		resume.setId(UUID.randomUUID().toString());
		clients.add(resume);
		when(recherche.tousDuneSociete(details)).thenReturn(clients);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(clients.get(0).getId());
	}
	
	@Test
	public void peutAjouterUnClientAUneSociete() {
		DetailsSociete details = laRechercheRetourne();
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("nom", "Saban");
		formulaire.add("prenom", "JR");
		formulaire.add("email", "a@a.com");
		formulaire.add("dateDeNaissance", "01/01/1991");
		formulaire.add("lieuDeNaissance", "Paris");
		formulaire.add("metier", "Etudiant");
		formulaire.add("nationalite", "Française");
		formulaire.add("adresse1", "1 rue du Black");
		formulaire.add("adresse2", "test");
		formulaire.add("codePostal", "33000");
		formulaire.add("telephonePortable", "0706080910");
		formulaire.add("telephoneFixe", "0506070809");
		
		ressource.ajouteClient(formulaire);
		
		assertThat(ressource.getStatus()).isEqualTo(Status.SUCCESS_ACCEPTED);
		ArgumentCaptor<AjoutClientMessage> capteur = ArgumentCaptor.forClass(AjoutClientMessage.class);
		verify(busCommande).envoie(capteur.capture());
		AjoutClientMessage commande = capteur.getValue();
		assertThat(commande.idSociete).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.nom).isEqualTo("Saban");
		assertThat(commande.prenom).isEqualTo("JR");
		assertThat(commande.email).isEqualTo("a@a.com");
		assertThat(commande.dateDeNaissance).isEqualTo("01/01/1991");
		assertThat(commande.lieuDeNaissance).isEqualTo("Paris");
		assertThat(commande.metier).isEqualTo("Etudiant");
		assertThat(commande.nationalite).isEqualTo("Française");
		assertThat(commande.adresse1).isEqualTo("1 rue du Black");
		assertThat(commande.adresse2).isEqualTo("test");
		assertThat(commande.codePostal).isEqualTo("33000");
		assertThat(commande.telephonePortable).isEqualTo("0706080910");
		assertThat(commande.telephoneFixe).isEqualTo("0506070809");
	}
	
	private DetailsSociete laRechercheRetourne() {
		DetailsSociete details = new DetailsSociete();
		UUID id = UUID.randomUUID();
		details.setId(id.toString());
		idUtilisateur = UUID.randomUUID();
		when(rechercheSocietes.societeDeLUtilisateur(idUtilisateur)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsSociete details) {
		Session.ajoute(idUtilisateur.toString());
		RessourceHelper.initialise(ressource).avec("idUtilisateur", idUtilisateur);
	}
	
	private BusCommande busCommande;
	private RechercheClients recherche;
	private RechercheSocietes rechercheSocietes;
	private ClientsRessource ressource;
	private UUID idUtilisateur;
	
}
