package fr.epsi.agora.commande;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.Test;

import fr.epsi.agora.commande.societe.CreationSocieteMessage;

public class BusCommandeTest {

	@Test
	public void peutEnregistrerUnHandler() {
		BusCommande busCommande = new BusCommande(mock(FournisseurMongoSession.class));
		CreationSocieteCommandeHandlerCommande handler = new CreationSocieteCommandeHandlerCommande();
		busCommande.enregistreHandler(handler);
		CreationSocieteMessage commande = new CreationSocieteMessage("552-120-222 00013", "Société générale");
		
		busCommande.envoie(commande);
		
		assertThat(handler.derniereCommande).isEqualTo(commande);
	}
	
	private static class CreationSocieteCommandeHandlerCommande implements HandlerCommande<CreationSocieteMessage> {

		@Override
		public Object execute(CreationSocieteMessage commande) {
			this.derniereCommande = commande;
			return UUID.randomUUID();
		}

		@Override
		public Class<CreationSocieteMessage> typeCommande() {
			return CreationSocieteMessage.class;
		}
		
		private CreationSocieteMessage derniereCommande;
		
	}
	
}
