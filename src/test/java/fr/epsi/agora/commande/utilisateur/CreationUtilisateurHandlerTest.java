package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.junit.Rule;
import org.junit.Test;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;
import fr.epsi.agora.persistance.fake.AvecEntrepots;

public class CreationUtilisateurHandlerTest {

	@Rule
	public AvecEntrepots entrepots = new AvecEntrepots();
	
	@Test
	public void peutCreerUtilisateur() {
		CreationUtilisateurMessage commande = new CreationUtilisateurMessage("Levacher", "Vincent", "a@a.com", "pass");
		
		UUID idUtilisateur = new CreationUtilisateurHandler().execute(commande);
		
		assertThat(idUtilisateur).isNotNull();
		assertThat(Entrepots.utilisateurs().get(idUtilisateur)).isNotNull();
		Utilisateur utilisateur = Entrepots.utilisateurs().get(idUtilisateur).orNull();
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateur.getAdresse()).isEmpty();
		assertThat(utilisateur.getTelephone()).isEmpty();
		assertThat(utilisateur.getDerniereConnexion()).isNull();
		assertThat(utilisateur.isConnecte()).isFalse();
	}
	
}
