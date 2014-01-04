package fr.epsi.agora.web.ressource.utilisateur;

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
import org.restlet.data.MediaType;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.commande.utilisateur.CreationUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class UtilisateursRessourceTest {

	@Before
	public void setUp() throws Exception {
		busCommande = mock(BusCommande.class);
		when(busCommande.envoie(any(Message.class))).thenReturn(Futures.<Object>immediateFuture(UUID.randomUUID()));
		recherche = mock(RechercheUtilisateurs.class);
		ressource = new UtilisateursRessource(busCommande, recherche);
		RessourceHelper.initialise(ressource);
	}
	
	@Test
	public void peutAfficherTousLesUtilisateurs() throws IOException {
		List<DetailsUtilisateur> utilisateurs = Lists.newArrayList();
		DetailsUtilisateur details = new DetailsUtilisateur();
		details.setId(UUID.randomUUID().toString());
		utilisateurs.add(details);
		when(recherche.tous()).thenReturn(utilisateurs);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getText()).contains(utilisateurs.get(0).getId());
	}
	
	@Test
	public void peutCreerUnUtilisateur() throws IOException {
		JacksonRepresentation<DetailsUtilisateur> json = new JacksonRepresentation<DetailsUtilisateur>(MediaType.APPLICATION_JSON, unUtilisateur());
		
		ressource.cree(json);
		
		ArgumentCaptor<CreationUtilisateurMessage> capteur = ArgumentCaptor.forClass(CreationUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		CreationUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.nom).isEqualTo("Levacher");
		assertThat(commande.prenom).isEqualTo("Vincent");
		assertThat(commande.email).isEqualTo("a@a.com");
		assertThat(commande.motDePasse).isEqualTo("pass");
		assertThat(commande.adresse).isEqualTo("1 rue Test");
		assertThat(commande.telephone).isEqualTo("0607080910");
	}
	
	private DetailsUtilisateur unUtilisateur() {
		DetailsUtilisateur details = new DetailsUtilisateur();
		details.setNom("Levacher");
		details.setPrenom("Vincent");
		details.setEmail("a@a.com");
		details.setMotDePasse("pass");
		details.setAdresse("1 rue Test");
		details.setTelephone("0607080910");
		return details;
	}
	
	private BusCommande busCommande;
	private UtilisateursRessource ressource;
	private RechercheUtilisateurs recherche;
	
}
