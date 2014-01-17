package fr.epsi.agora.commande.constat;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.FakeFabriqueConstat;

public class SuppressionMediaAnnexeHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutSupprimerUnMediaAnnexeDunConstat() {
		Constat constat = Entrepots.constats().ajoute(FakeFabriqueConstat.nouveau());
		SuppressionMediaAnnexeMessage commande = new SuppressionMediaAnnexeMessage(constat.getId(), "\\\\annexes\\photo.jpg");
		
		new SuppressionMediaAnnexeHandler().execute(commande);
		
		Constat constatModifie = Entrepots.constats().get(constat.getId()).orNull();
		assertThat(constatModifie).isNotNull();
		assertThat(constatModifie.getAudios()).excludes("\\\\annexes\\photo.jpg");
	}
	
}
