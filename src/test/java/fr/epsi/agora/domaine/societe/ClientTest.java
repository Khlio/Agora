package fr.epsi.agora.domaine.societe;

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
	public void peutDonnerUnNom() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getNom()).isEqualTo("Saban");
	}
	
	@Test
	public void peutDonnerUnPrenom() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getPrenom()).isEqualTo("JR");
	}
	
	@Test
	public void peutDonnerUnEmail() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getEmail()).isEqualTo("a@a.com");
	}
	
	@Test
	public void peutDonnerUneDateDeNaissance() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getDateDeNaissance()).isEqualTo("01/01/1991");
	}
	
	@Test
	public void peutDonnerUnLieuDeNaissance() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getLieuDeNaissance()).isEqualTo("Paris");
	}
	
	@Test
	public void peutDonnerUnMetier() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getMetier()).isEqualTo("Etudiant");
	}
	
	@Test
	public void peutDonnerUneNationalite() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getNationalite()).isEqualTo("Française");
	}
	
	@Test
	public void peutDonnerUneAdresse() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getAdresse()).isEqualTo("1 rue du Black");
	}
	
	@Test
	public void peutDonnerUnTelephone() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getTelephone()).isEqualTo("0706080910");
	}
	
}
