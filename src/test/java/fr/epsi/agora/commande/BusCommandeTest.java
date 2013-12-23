package fr.epsi.agora.commande;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.Test;

import fr.epsi.agora.commande.utilisateur.CreationUtilisateurMessage;

public class BusCommandeTest {

	@Test
	public void peutEnregistrerUnHandler() {
		BusCommande busCommande = new BusCommande(mock(FournisseurMongoSession.class));
		CreationUtilisateurCommandeHandlerCommande handler = new CreationUtilisateurCommandeHandlerCommande();
		busCommande.enregistreHandler(handler);
		CreationUtilisateurMessage commande = new CreationUtilisateurMessage("Levacher", "Vincent", "a@a.com", "pass");
		
		busCommande.envoie(commande);
		
		assertThat(handler.derniereCommande).isEqualTo(commande);
	}
	
	private static class CreationUtilisateurCommandeHandlerCommande implements HandlerCommande<CreationUtilisateurMessage> {

		@Override
		public Object execute(CreationUtilisateurMessage commande) {
			this.derniereCommande = commande;
			return UUID.randomUUID();
		}

		@Override
		public Class<CreationUtilisateurMessage> typeCommande() {
			return CreationUtilisateurMessage.class;
		}
		
		private CreationUtilisateurMessage derniereCommande;
		
	}
	
}
