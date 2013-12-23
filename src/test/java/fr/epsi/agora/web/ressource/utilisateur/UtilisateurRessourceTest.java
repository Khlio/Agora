package fr.epsi.agora.web.ressource.utilisateur;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.SuppressionUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;
import fr.epsi.agora.web.representation.ModeleEtVue;
import fr.epsi.agora.web.ressource.RessourceHelper;

public class UtilisateurRessourceTest {

	@Before
	public void setUp() {
		recherche = mock(RechercheUtilisateurs.class);
		busCommande = mock(BusCommande.class);
		ressource = new UtilisateurRessource(busCommande, recherche);
	}
	
	@Test
	public void peutAfficherLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		ModeleEtVue modeleEtVue = ressource.represente();
		
		assertThat(modeleEtVue).isNotNull();
		assertThat(modeleEtVue.getTexte()).contains(details.getId());
		assertThat(modeleEtVue.getData()).includes(entry("utilisateur", details));
	}
	
	@Test
	public void peutSupprimerLUtilisateur() {
		DetailsUtilisateur details = laRechercheRetourne();
		initialiseRessource(details);
		
		ressource.supprime();
		
		ArgumentCaptor<SuppressionUtilisateurMessage> capteur = ArgumentCaptor.forClass(SuppressionUtilisateurMessage.class);
		verify(busCommande).envoie(capteur.capture());
		SuppressionUtilisateurMessage commande = capteur.getValue();
		assertThat(commande.idUtilisateur).isEqualTo(UUID.fromString(details.getId()));
		//TODO vérifier que l'utilisateur n'est plus dans l'entrepôt
	}
	
	private DetailsUtilisateur laRechercheRetourne() {
		DetailsUtilisateur details = new DetailsUtilisateur();
		UUID uuid = UUID.randomUUID();
		details.setId(uuid.toString());
		when(recherche.detailsDe(uuid)).thenReturn(details);
		return details;
	}
	
	private void initialiseRessource(DetailsUtilisateur detailsUtilisateur) {
		RessourceHelper.initialise(ressource).avec("id", detailsUtilisateur.getId());
	}
	
	private RechercheUtilisateurs recherche;
	private UtilisateurRessource ressource;
	private BusCommande busCommande;
	
}
