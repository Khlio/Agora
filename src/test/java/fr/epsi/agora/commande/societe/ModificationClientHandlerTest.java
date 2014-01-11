package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FakeFabriqueClient;

public class ModificationClientHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutModifierClient() {
		Client client = Entrepots.clients().ajoute(FakeFabriqueClient.nouveau());
		ModificationClientMessage commande = new ModificationClientMessage(client.getId(), client.getNom(), client.getPrenom(), "b@b.com",
				client.getDateDeNaissance(), client.getLieuDeNaissance(), client.getMetier(), client.getNationalite(), client.getAdresse(), client.getTelephone());
		
		new ModificationClientHandler().execute(commande);
		
		Client clientModifie = Entrepots.clients().get(client.getId()).orNull();
		assertThat(clientModifie).isNotNull();
		assertThat(clientModifie.getNom()).isEqualTo(client.getNom());
		assertThat(clientModifie.getPrenom()).isEqualTo(client.getPrenom());
		assertThat(clientModifie.getEmail()).isEqualTo("b@b.com");
		assertThat(clientModifie.getDateDeNaissance()).isEqualTo(client.getDateDeNaissance());
		assertThat(clientModifie.getLieuDeNaissance()).isEqualTo(client.getLieuDeNaissance());
		assertThat(clientModifie.getMetier()).isEqualTo(client.getMetier());
		assertThat(clientModifie.getAdresse()).isEqualTo(client.getAdresse());
		assertThat(clientModifie.getTelephone()).isEqualTo(client.getTelephone());
	}
	
}
