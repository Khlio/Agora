package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class CreationUtilisateurHandlerTest extends HandlerCommandeRegle {
	
	@Test
	public void peutCreerUtilisateur() {
		CreationUtilisateurMessage commande = new CreationUtilisateurMessage("Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "0607080910");
		
		UUID idUtilisateur = new CreationUtilisateurHandler().execute(commande);
		
		assertThat(idUtilisateur).isNotNull();
		Utilisateur utilisateur = Entrepots.utilisateurs().get(idUtilisateur).orNull();
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateur.getAdresse()).isEqualTo("1 rue Test");
		assertThat(utilisateur.getTelephone()).isEqualTo("0607080910");
		assertThat(utilisateur.getDerniereConnexion()).isNull();
		assertThat(utilisateur.isConnecte()).isFalse();
	}
	
}
