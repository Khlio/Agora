package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

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
		AjoutClientMessage commande = new AjoutClientMessage(societe.getId(), "Saban", "JR", "a@a.com", "01/01/1991", "Paris", "Etudiant", "Française",
				"1 rue du Black", "test", "33000", "0706080910", "0506070809");
		
		new AjoutClientHandler().execute(commande);
		
		assertThat(societe.getClients()).hasSize(1);
		Client client = societe.getClients().get(0);
		assertThat(client.getNom()).isEqualTo("Saban");
		assertThat(client.getPrenom()).isEqualTo("JR");
		assertThat(client.getEmail()).isEqualTo("a@a.com");
		assertThat(client.getDateDeNaissance()).isEqualTo("01/01/1991");
		assertThat(client.getLieuDeNaissance()).isEqualTo("Paris");
		assertThat(client.getMetier()).isEqualTo("Etudiant");
		assertThat(client.getNationalite()).isEqualTo("Française");
		assertThat(client.getAdresse1()).isEqualTo("1 rue du Black");
		assertThat(client.getAdresse2()).isEqualTo("test");
		assertThat(client.getCodePostal()).isEqualTo("33000");
		assertThat(client.getTelephonePortable()).isEqualTo("0706080910");
		assertThat(client.getTelephoneFixe()).isEqualTo("0506070809");
		assertThat(Entrepots.clients().get(client.getId()).isPresent()).isTrue();
	}
	
}
