package fr.epsi.agora.commande.utilisateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Rule;
import org.junit.Test;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.utilisateur.FabriqueUtlisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;
import fr.epsi.agora.persistance.fake.AvecEntrepots;

public class SuppressionUtilisateurHandlerTest {

	@Rule
	public AvecEntrepots entrepots = new AvecEntrepots();
	
	@Test
	public void peutSupprimerUtilisateur() {
		Utilisateur utilisateur = Entrepots.utilisateurs().ajoute(new FabriqueUtlisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass"));
		SuppressionUtilisateurMessage commande = new SuppressionUtilisateurMessage(utilisateur.getId());
		
		new SuppressionUtilisateurHandler().execute(commande);
		
		assertThat(Entrepots.utilisateurs().get(utilisateur.getId()).orNull()).isNull();
	}
	
}
