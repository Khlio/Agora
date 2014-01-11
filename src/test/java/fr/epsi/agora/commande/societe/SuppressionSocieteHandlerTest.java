package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;
import fr.epsi.agora.domaine.societe.Societe;

public class SuppressionSocieteHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutSupprimerSociete() {
		Societe societe = Entrepots.societes().ajoute(FakeFabriqueSociete.nouveau());
		SuppressionSocieteMessage commande = new SuppressionSocieteMessage(societe.getId());
		
		new SuppressionSocieteHandler().execute(commande);
		
		assertThat(Entrepots.societes().get(societe.getId()).orNull()).isNull();
	}
	
}
