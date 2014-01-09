package fr.epsi.agora.persistance.mongo;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.domaine.societe.FakeFabriqueClient;
import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Societe;

public class EntrepotSocietesMongoTest extends EntrepotAggregatsMongoTest<Societe> {

	@Override
	public void setUp() {
		entrepot = new EntrepotSocietesMongo(fournisseur);
	}

	@Override
	public void peutAjouter() {
		Societe societe = FakeFabriqueSociete.nouveau();
		
		entrepot.ajoute(societe);
		mongolinkRule.cleanSession();
		
		Societe societeTrouve = entrepot.get(societe.getId()).orNull();
		assertThat(societeTrouve).isNotNull();
		assertThat(societeTrouve.getSiret()).isEqualTo("552-120-222 00013");
		assertThat(societeTrouve.getNom()).isEqualTo("Société générale");
	}
	
	@Test
	public void peutPersisterUtilisateurs() {
		Societe societe = FakeFabriqueSociete.nouveau();
		societe.ajouteUtilisateur(FakeFabriqueUtilisateur.nouveau());
		
		entrepot.ajoute(societe);
		mongolinkRule.cleanSession();
		
		Societe societeTrouve = entrepot.get(societe.getId()).get();
		assertThat(societeTrouve.getUtilisateurs()).hasSize(1);
		assertThat(societeTrouve.getUtilisateurs().get(0).getNom()).isEqualTo("Levacher");
	}
	
	@Test
	public void peutPersisterClients() {
		Societe societe = FakeFabriqueSociete.nouveau();
		societe.ajouteClient(FakeFabriqueClient.nouveau());
		
		entrepot.ajoute(societe);
		mongolinkRule.cleanSession();
		
		Societe societeTrouve = entrepot.get(societe.getId()).get();
		assertThat(societeTrouve.getClients()).hasSize(1);
		assertThat(societeTrouve.getClients().get(0).getNom()).isEqualTo("Saban");
	}

	@Override
	public void peutModifier() {
		Societe societe = FakeFabriqueSociete.nouveau();
		entrepot.ajoute(societe);
		societe.setNom("test");
		
		entrepot.modifie(societe);
		mongolinkRule.cleanSession();
		
		Societe societeTrouve = entrepot.get(societe.getId()).get();
		assertThat(societeTrouve.getNom()).isEqualTo("test");
	}

	@Override
	public void peutSupprimer() {
		Societe societe = FakeFabriqueSociete.nouveau();
		entrepot.ajoute(societe);
		
		entrepot.supprime(societe);
		mongolinkRule.cleanSession();
		
		assertThat(entrepot.get(societe.getId()).isPresent()).isFalse();
	}

}
