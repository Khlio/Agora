package fr.epsi.agora.commande.constat;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.FakeFabriqueConstat;

public class ModificationConstatHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutModifierConstat() {
		Constat constat = Entrepots.constats().ajoute(FakeFabriqueConstat.nouveau());
		ModificationConstatMessage commande = new ModificationConstatMessage(constat.getId(), constat.getNom(), "test");
		
		new ModificationConstatHandler().execute(commande);
		
		Constat constatModifie = Entrepots.constats().get(constat.getId()).orNull();
		assertThat(constatModifie).isNotNull();
		assertThat(constatModifie.getNom()).isEqualTo(constat.getNom());
		assertThat(constatModifie.getAdresse()).isEqualTo("test");
	}
	
}
