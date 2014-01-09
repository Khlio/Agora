package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;
import fr.epsi.agora.domaine.societe.Societe;

public class AjoutClientHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutAjouterUnClient() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Entrepots.societes().ajoute(societe);
		AjoutClientMessage commande = new AjoutClientMessage(societe.getId(), "Saban", "JR", "a@a.com", new Date(), "Paris", "Etudiant", "Française",
				"1 rue du Black", "0706080910");
		
		new AjoutClientHandler().execute(commande);
		
		assertThat(societe.getClients()).hasSize(1);
		Client client = societe.getClients().get(0);
		assertThat(client.getNom()).isEqualTo("Saban");
		assertThat(client.getPrenom()).isEqualTo("JR");
		assertThat(client.getEmail()).isEqualTo("a@a.com");
		assertThat(client.getDateDeNaissance()).isNotNull();
		assertThat(client.getLieuDeNaissance()).isEqualTo("Paris");
		assertThat(client.getMetier()).isEqualTo("Etudiant");
		assertThat(client.getNationalite()).isEqualTo("Française");
		assertThat(client.getAdresse()).isEqualTo("1 rue du Black");
		assertThat(client.getTelephone()).isEqualTo("0706080910");
	}
	
}
