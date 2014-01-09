package fr.epsi.agora.persistance.mongo;

import static org.fest.assertions.Assertions.assertThat;
import fr.epsi.agora.domaine.client.Client;
import fr.epsi.agora.domaine.client.FakeFabriqueClient;
import fr.epsi.agora.domaine.utilisateur.FakeFabriqueUtilisateur;

public class EntrepotClientsMongoTest extends EntrepotAggregatsMongoTest<Client> {
	
	@Override
	public void setUp() {
		entrepot = new EntrepotClientsMongo(fournisseur);
	}
	
	@Override
	public void peutAjouter() {
		Client client = FakeFabriqueClient.nouveau();
		client.ajouteUtilisateur(FakeFabriqueUtilisateur.nouveau());
		
		entrepot.ajoute(client);
		mongolinkRule.cleanSession();
		
		Client clientTrouve = entrepot.get(client.getId()).orNull();
		assertThat(clientTrouve).isNotNull();
		assertThat(clientTrouve.getNom()).isEqualTo("Saban");
		assertThat(clientTrouve.getPrenom()).isEqualTo("JR");
		assertThat(clientTrouve.getEmail()).isEqualTo("a@a.com");
		assertThat(clientTrouve.getDateDeNaissance()).isNotNull();
		assertThat(clientTrouve.getLieuDeNaissance()).isEqualTo("Paris");
		assertThat(clientTrouve.getMetier()).isEqualTo("Etudiant");
		assertThat(clientTrouve.getNationalite()).isEqualTo("Française");
		assertThat(clientTrouve.getAdresse()).isEqualTo("1 rue du Black");
		assertThat(clientTrouve.getTelephone()).isEqualTo("0706080910");
		assertThat(clientTrouve.getSociete()).isNotNull();
		assertThat(clientTrouve.getUtilisateurs()).hasSize(1);
	}
	
	@Override
	public void peutModifier() {
		Client client = FakeFabriqueClient.nouveau();
		entrepot.ajoute(client);
		client.setPrenom("Jean-Roland");
		
		entrepot.modifie(client);
		mongolinkRule.cleanSession();
		
		Client clientTrouve = entrepot.get(client.getId()).orNull();
		assertThat(clientTrouve).isNotNull();
		assertThat(client.getPrenom()).isEqualTo("Jean-Roland");
	}
	
	@Override
	public void peutSupprimer() {
		Client client = FakeFabriqueClient.nouveau();
		entrepot.ajoute(client);
		
		entrepot.supprime(client);
		mongolinkRule.cleanSession();
		
		assertThat(entrepot.get(client.getId()).isPresent()).isFalse();
	}
	
}
