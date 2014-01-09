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
import org.restlet.representation.Representation;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ModificationUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class UtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		busCommande = mock(BusCommande.class);
		ressource = new UtilisateurRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherLUtilisateur() throws IOException {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		Representation represente = ressource.represente();
		
		assertThat(represente).isNotNull();
		assertThat(represente.getMediaType()).isEqualTo(MediaType.APPLICATION_JSON);
		assertThat(represente.getText()).contains(details.getId());
	}
	
	@Test
	public void peutModifierLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		details.setNom("Levacher");
		initialiseRessource(details);
		Form formulaire = new Form();
		formulaire.add("nom", details.getNom());
		formulaire.add("email", "b@b.com");
		
		ressource.modifie(formulaire);
		
		ArgumentCaptor<ModificationUtilisateurMessage> capteur = ArgumentCaptor.forClass(ModificationUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		ModificationUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
		assertThat(commande.nom).isEqualTo(details.getNom());
		assertThat(commande.email).isEqualTo("b@b.com");
	}
	
	@Test
	public void peutSupprimerLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		ressource.supprime();
		
		ArgumentCaptor<SuppressionUtilisateurMessage> capteur = ArgumentCaptor.forClass(SuppressionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		SuppressionUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.id).isEqualTo(UUID.fromString(details.getId()));
	}
	
	private DetailsUtilisateur laRechercheRetourne() {
		DetailsUtilisateur details = new DetailsUtilisateur();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsUtilisateur details) {
		RessourceHelper.initialise(ressource).avec("idUtilisateur", details.getId());
	}
	
	private RechercheUtilisateurs recherche;
	private UtilisateurRessource ressource;
	private BusCommande busCommande;
	
}
