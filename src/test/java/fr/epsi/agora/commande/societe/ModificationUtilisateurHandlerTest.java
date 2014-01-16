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
		ModificationUtilisateurMessage commande = new ModificationUtilisateurMessage(utilisateur.getId(), "LEVA", utilisateur.getPrenom(), utilisateur.getMotDePasse(),
				utilisateur.getAdresse(), utilisateur.getCodePostal(), utilisateur.getTelephone());
		
		new ModificationUtilisateurHandler().execute(commande);
		
		Utilisateur utilisateurModifie = Entrepots.utilisateurs().get(utilisateur.getId()).orNull();
		assertThat(utilisateurModifie).isNotNull();
		assertThat(utilisateurModifie.getNom()).isEqualTo("LEVA");
		assertThat(utilisateurModifie.getPrenom()).isEqualTo(utilisateur.getPrenom());
		assertThat(utilisateurModifie.getMotDePasse()).isEqualTo(utilisateur.getMotDePasse());
		assertThat(utilisateurModifie.getAdresse()).isEqualTo(utilisateur.getAdresse());
		assertThat(utilisateurModifie.getCodePostal()).isEqualTo(utilisateur.getCodePostal());
		assertThat(utilisateurModifie.getTelephone()).isEqualTo(utilisateur.getTelephone());
	}
	
}
