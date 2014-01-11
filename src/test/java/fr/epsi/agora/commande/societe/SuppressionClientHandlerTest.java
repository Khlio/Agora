package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FakeFabriqueClient;

public class SuppressionClientHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutSupprimerClient() {
		Client client = Entrepots.clients().ajoute(FakeFabriqueClient.nouveau());
		SuppressionClientMessage commande = new SuppressionClientMessage(client.getId());
		
		new SuppressionClientHandler().execute(commande);
		
		assertThat(Entrepots.clients().get(client.getId()).orNull()).isNull();
	}
	
}
