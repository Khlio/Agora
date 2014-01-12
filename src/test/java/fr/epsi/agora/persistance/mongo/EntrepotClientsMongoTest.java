package fr.epsi.agora.persistance.mongo;

import static org.fest.assertions.Assertions.assertThat;
import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.FakeFabriqueClient;

public class EntrepotClientsMongoTest extends EntrepotAggregatsMongoTest<Client> {
	
	@Override
	public void setUp() {
		entrepot = new EntrepotClientsMongo(fournisseur);
	}
	
	@Override
	public void peutAjouter() {
		Client client = FakeFabriqueClient.nouveau();
		
		entrepot.ajoute(client);
		mongolinkRule.cleanSession();
		
		Client clientTrouve = entrepot.get(client.getId()).orNull();
		assertThat(clientTrouve).isNotNull();
		assertThat(clientTrouve.getNom()).isEqualTo("Saban");
		assertThat(clientTrouve.getPrenom()).isEqualTo("JR");
		assertThat(clientTrouve.getEmail()).isEqualTo("a@a.com");
		assertThat(clientTrouve.getDateDeNaissance()).isEqualTo("01/01/1991");
		assertThat(clientTrouve.getLieuDeNaissance()).isEqualTo("Paris");
		assertThat(clientTrouve.getMetier()).isEqualTo("Etudiant");
		assertThat(clientTrouve.getNationalite()).isEqualTo("Française");
		assertThat(clientTrouve.getAdresse1()).isEqualTo("1 rue du Black");
		assertThat(clientTrouve.getAdresse2()).isEqualTo("test");
		assertThat(clientTrouve.getCodePostal()).isEqualTo("33000");
		assertThat(clientTrouve.getTelephonePortable()).isEqualTo("0706080910");
		assertThat(clientTrouve.getTelephoneFixe()).isEqualTo("0506070809");
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
		assertThat(clientTrouve.getPrenom()).isEqualTo("Jean-Roland");
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
