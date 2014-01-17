package fr.epsi.agora.commande.constat;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.FakeFabriqueConstat;

public class SuppressionMediaAudioHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutSupprimerUnMediaAudioDunConstat() {
		Constat constat = Entrepots.constats().ajoute(FakeFabriqueConstat.nouveau());
		SuppressionMediaAudioMessage commande = new SuppressionMediaAudioMessage(constat.getId(), "\\\\medias\\audio.mp3");
		
		new SuppressionMediaAudioHandler().execute(commande);
		
		Constat constatModifie = Entrepots.constats().get(constat.getId()).orNull();
		assertThat(constatModifie).isNotNull();
		assertThat(constatModifie.getAudios()).excludes("\\\\medias\\audio.mp3");
	}
	
}
