package fr.epsi.agora.commande.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.commande.HandlerCommandeRegle;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class ModificationUtilisateurHandlerTest extends HandlerCommandeRegle {
	
	@Test
	public void peutModifierUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(FakeFabriqueUtilisateur.nouveau());
		ModificationUtilisateurMessage commande = new ModificationUtilisateurMessage(utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(),
				"b@b.com", utilisateur.getMotDePasse(), utilisateur.getAdresse(), utilisateur.getTelephone());
		
		new ModificationUtilisateurHandler().execute(commande);
		
		Utilisateur utilisateurModifie = Entrepots.utilisateurs().get(utilisateur.getId()).orNull();
		assertThat(utilisateurModifie).isNotNull();
		assertThat(utilisateurModifie.getNom()).isEqualTo(utilisateur.getNom());
		assertThat(utilisateurModifie.getPrenom()).isEqualTo(utilisateur.getPrenom());
		assertThat(utilisateurModifie.getEmail()).isEqualTo("b@b.com");
		assertThat(utilisateurModifie.getMotDePasse()).isEqualTo(utilisateur.getMotDePasse());
		assertThat(utilisateurModifie.getAdresse()).isEqualTo(utilisateur.getAdresse());
		assertThat(utilisateurModifie.getTelephone()).isEqualTo(utilisateur.getTelephone());
	}
	
}
