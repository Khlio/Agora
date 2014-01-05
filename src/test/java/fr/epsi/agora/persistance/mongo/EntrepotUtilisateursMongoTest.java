package fr.epsi.agora.persistance.mongo;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mongolink.MongoSession;
import org.mongolink.test.MongolinkRule;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.utilisateur.FabriqueUtilisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class EntrepotUtilisateursMongoTest {

	@Rule
	public MongolinkRule mongolinkRule = MongolinkRule.withPackage("fr.epsi.agora.persistance.mongo.mapping");
	
	@Before
	public void setUp() {
		entrepot = new EntrepotUtilisateursMongo(new FournisseurMongoSession() {
			@Override
			public MongoSession get() {
				return mongolinkRule.getCurrentSession();
			}
			
			@Override
			public void nettoie() {
			}
		});
	}
	
	@Test
	public void peutAjouter() {
		Utilisateur utilisateur = new FabriqueUtilisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass");
		
		entrepot.ajoute(utilisateur);
		mongolinkRule.cleanSession();
		
		Utilisateur utilisateurTrouve = entrepot.get(utilisateur.getId()).orNull();
		assertThat(utilisateurTrouve).isNotNull();
		assertThat(utilisateurTrouve.getNom()).isEqualTo("Levacher");
		assertThat(utilisateurTrouve.getPrenom()).isEqualTo("Vincent");
		assertThat(utilisateurTrouve.getEmail()).isEqualTo("a@a.com");
		assertThat(utilisateurTrouve.getMotDePasse()).isEqualTo("pass");
		assertThat(utilisateurTrouve.getAdresse()).isEmpty();
		assertThat(utilisateurTrouve.getTelephone()).isEmpty();
		assertThat(utilisateurTrouve.getDerniereConnexion()).isNull();
		assertThat(utilisateurTrouve.isConnecte()).isFalse();
	}
	
	@Test
	public void peutSupprimer() {
		Utilisateur utilisateur = new FabriqueUtilisateur().nouveau("Levacher", "Vincent", "a@a.com", "pass");
		entrepot.ajoute(utilisateur);
		
		entrepot.supprime(utilisateur);
		mongolinkRule.cleanSession();
		
		assertThat(entrepot.get(utilisateur.getId()).isPresent()).isFalse();
	}
	
	private EntrepotUtilisateursMongo entrepot;
	
}
