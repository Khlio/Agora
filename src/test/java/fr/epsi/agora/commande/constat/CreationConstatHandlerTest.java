package fr.epsi.agora.commande.constat;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.joda.time.DateTime;
import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FakeFabriqueClient;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class CreationConstatHandlerTest extends HandlerCommandeRegle {

	@Test
	public void peutCreerConstat() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		Entrepots.utilisateurs().ajoute(utilisateur);
		Client client = FakeFabriqueClient.nouveau();
		Entrepots.clients().ajoute(client);
		CreationConstatMessage commande = new CreationConstatMessage("Tout cassé", "1 rue du Bordel", DateTime.now(), "", utilisateur.getId(), client.getId());
		
		UUID idConstat = new CreationConstatHandler().execute(commande);
		
		assertThat(idConstat).isNotNull();
		Constat constat = Entrepots.constats().get(idConstat).orNull();
		assertThat(constat).isNotNull();
		assertThat(constat.getNom()).isEqualTo("Tout cassé");
		assertThat(constat.getDate()).isNotNull();
		assertThat(constat.getGeolocalisation()).isEqualTo("");
		assertThat(constat.getUtilisateur()).isEqualTo(utilisateur);
		assertThat(constat.getClient()).isEqualTo(client);
	}
	
}
