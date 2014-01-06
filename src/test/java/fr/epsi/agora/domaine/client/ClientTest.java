package fr.epsi.agora.domaine.client;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ClientTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client).isNotNull();
		assertThat(client.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerCesAttributs() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getNom()).isEqualTo("Saban");
		assertThat(client.getPrenom()).isEqualTo("JR");
		assertThat(client.getEmail()).isEqualTo("a@a.com");
		assertThat(client.getDateNaissance()).isNotNull();
		assertThat(client.getLieuNaissance()).isEqualTo("Paris");
		assertThat(client.getMetier()).isEqualTo("Etudiant");
		assertThat(client.getNationalite()).isEqualTo("Française");
		assertThat(client.getAdresse()).isEqualTo("1 rue du Black");
		assertThat(client.getTelephone()).isEqualTo("0706080910");
	}
	
}
