package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.FabriqueUtilisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;
import fr.epsi.agora.persistance.fake.AvecEntrepots;

public class ModificationUtilisateurHandlerTest {

	@Rule
	public AvecEntrepots entrepots = new AvecEntrepots();
	
	@Test
	public void peutModifierUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(new FabriqueUtilisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass"));
		ModificationUtilisateurMessage commande = new ModificationUtilisateurMessage(utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(),
				"b@b.com", null, utilisateur.getAdresse(), utilisateur.getTelephone());
		
		new ModificationUtilisateurHandler().execute(commande);
		
		Utilisateur utilisateurModifie = Entrepots.utilisateurs().get(utilisateur.getId()).orNull();
		assertThat(utilisateurModifie).isNotNull();
		assertThat(utilisateurModifie.getNom()).isEqualTo("Levacher");
		assertThat(utilisateurModifie.getEmail()).isEqualTo("b@b.com");
		assertThat(utilisateurModifie.getMotDePasse()).isEqualTo("pass");
	}
	
}
