package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FakeFabriqueClient;
import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;
import fr.epsi.agora.domaine.societe.Societe;

public class SuppressionClientHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutSupprimerClient() {
		Societe societe = Entrepots.societes().ajoute(FakeFabriqueSociete.nouveau());
		Client client = Entrepots.clients().ajoute(FakeFabriqueClient.nouveau());
		societe.ajouteClient(client.getId());
		SuppressionClientMessage commande = new SuppressionClientMessage(client.getId(), societe.getId());
		
		new SuppressionClientHandler().execute(commande);
		
		assertThat(Entrepots.clients().get(client.getId()).orNull()).isNull();
		assertThat(Entrepots.societes().get(societe.getId()).get().getClients()).isEmpty();
	}
	
}
