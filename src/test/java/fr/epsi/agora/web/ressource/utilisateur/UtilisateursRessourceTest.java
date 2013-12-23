package fr.epsi.agora.web.ressource.utilisateur;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.restlet.data.Form;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.Message;
import fr.epsi.agora.commande.utilisateur.CreationUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;
import fr.epsi.agora.web.representation.ModeleEtVue;
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
	public void peutAfficherTousLesUtilisateurs() {
		List<DetailsUtilisateur> utilisateurs = Lists.newArrayList();
		DetailsUtilisateur details = new DetailsUtilisateur();
		details.setId(UUID.randomUUID().toString());
		utilisateurs.add(details);
		when(recherche.tous()).thenReturn(utilisateurs);
		
		ModeleEtVue represente = ressource.represente();
		
		assertThat(represente.getTexte()).contains(utilisateurs.get(0).getId());
		assertThat(represente.getData()).includes(entry("utilisateurs", utilisateurs));
	}
	
	@Test
	public void peutCreerUnUtilisateur() {
		//TODO Teste à refaire
		Form formulaire = new Form();
		formulaire.add("nom", "Levacher");
		formulaire.add("prenom", "Vincent");
		formulaire.add("email", "a@a.com");
		formulaire.add("motDePasse", "pass");
		formulaire.add("adresse", "1 rue Test");
		formulaire.add("telephone", "0607080910");
		
		ressource.cree(formulaire);
		
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
	
	private BusCommande busCommande;
	private UtilisateursRessource ressource;
	private RechercheUtilisateurs recherche;
	
}
