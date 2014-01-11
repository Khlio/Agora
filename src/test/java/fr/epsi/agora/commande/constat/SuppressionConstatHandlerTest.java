package fr.epsi.agora.commande.constat;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.FakeFabriqueConstat;

public class SuppressionConstatHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutSupprimerConstat() {
		Constat constat = Entrepots.constats().ajoute(FakeFabriqueConstat.nouveau());
		SuppressionConstatMessage commande = new SuppressionConstatMessage(constat.getId());
		
		new SuppressionConstatHandler().execute(commande);
		
		assertThat(Entrepots.constats().get(constat.getId()).orNull()).isNull();
	}
	
}
