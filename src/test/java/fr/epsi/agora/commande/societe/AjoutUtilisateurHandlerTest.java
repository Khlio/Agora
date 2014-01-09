package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;
import fr.epsi.agora.domaine.societe.Societe;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class AjoutUtilisateurHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutAjouterUnUtilisateur() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Entrepots.societes().ajoute(societe);
		AjoutUtilisateurMessage commande = new AjoutUtilisateurMessage(societe.getId(), "Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "0607080910");
		
		new AjoutUtilisateurHandler().execute(commande);
		
		assertThat(societe.getUtilisateurs()).hasSize(1);
		Utilisateur utilisateur = societe.getUtilisateurs().get(0);
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateur.getAdresse()).isEqualTo("1 rue Test");
		assertThat(utilisateur.getTelephone()).isEqualTo("0607080910");
	}
	
}
