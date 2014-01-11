package fr.epsi.agora.persistance.mongo;

import static org.fest.assertions.Assertions.assertThat;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class EntrepotUtilisateursMongoTest extends EntrepotAggregatsMongoTest<Utilisateur> {

	@Override
	public void setUp() {
		entrepot = new EntrepotUtilisateursMongo(fournisseur);
	}
	
	@Override
	public void peutAjouter() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		entrepot.ajoute(utilisateur);
		mongolinkRule.cleanSession();
		
		Utilisateur utilisateurTrouve = entrepot.get(utilisateur.getId()).orNull();
		assertThat(utilisateurTrouve).isNotNull();
		assertThat(utilisateurTrouve.getNom()).isEqualTo("Levacher");
		assertThat(utilisateurTrouve.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateurTrouve.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateurTrouve.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateurTrouve.getAdresse()).isEqualTo("1 rue Test");
		assertThat(utilisateurTrouve.getTelephone()).isEqualTo("0607080910");
	}
	
	@Override
	public void peutModifier() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		entrepot.ajoute(utilisateur);
		utilisateur.setEmail("b@b.com");
		
		entrepot.modifie(utilisateur);
		mongolinkRule.cleanSession();
		
		Utilisateur utilisateurTrouve = entrepot.get(utilisateur.getId()).orNull();
		assertThat(utilisateurTrouve).isNotNull();
		assertThat(utilisateurTrouve.getEmail()).isEqualTo("b@b.com");
	}
	
	@Override
	public void peutSupprimer() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		entrepot.ajoute(utilisateur);
		
		entrepot.supprime(utilisateur);
		mongolinkRule.cleanSession();
		
		assertThat(entrepot.get(utilisateur.getId()).isPresent()).isFalse();
	}
	
}
