package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.Societe;

public class CreationSocieteHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutCreerSociete() {
		CreationSocieteMessage commande = new CreationSocieteMessage("552-120-222 00013", "Société générale");
		
		UUID idSociete = new CreationSocieteHandler().execute(commande);
		
		assertThat(idSociete).isNotNull();
		Societe societe = Entrepots.societes().get(idSociete).orNull();
		assertThat(societe).isNotNull();
		assertThat(societe.getSiret()).isEqualTo("552-120-222 00013");
		assertThat(societe.getNom()).isEqualTo("Société générale");
	}
	
}
