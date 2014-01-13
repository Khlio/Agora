package fr.epsi.agora.domaine.societe;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class SocieteTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Societe societe = FakeFabriqueSociete.nouveau();
		
		assertThat(societe).isNotNull();
		assertThat(societe.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerUnSiret() {
		Societe societe = FakeFabriqueSociete.nouveau();
		
		assertThat(societe.getSiret()).isEqualTo("552-120-222 00013");
	}
	
	@Test
	public void peutDonnerUnNom() {
		Societe societe = FakeFabriqueSociete.nouveau();
		
		assertThat(societe.getNom()).isEqualTo("Société générale");
	}
	
	@Test
	public void peutAjouterUnUtilisateur() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		societe.ajouteUtilisateur(utilisateur.getId());
		
		assertThat(societe.getUtilisateurs()).isNotNull();
		assertThat(societe.getUtilisateurs()).hasSize(1);
		assertThat(societe.getUtilisateurs()).contains(utilisateur.getId());
	}
	
	@Test
	public void peutSupprimerUnUtilisateur() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		societe.ajouteUtilisateur(utilisateur.getId());
		
		societe.supprimeUtilisateur(utilisateur.getId());
		
		assertThat(societe.getUtilisateurs()).isEmpty();
		assertThat(societe.getUtilisateurs()).excludes(utilisateur.getId());
	}
	
	@Test
	public void peutAjouterUnClient() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Client client = FakeFabriqueClient.nouveau();
		
		societe.ajouteClient(client.getId());
		
		assertThat(societe.getClients()).isNotNull();
		assertThat(societe.getClients()).hasSize(1);
		assertThat(societe.getClients()).contains(client.getId());
	}
	
	@Test
	public void peutSupprimerUnClient() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Client client = FakeFabriqueClient.nouveau();
		societe.ajouteClient(client.getId());
		
		societe.supprimeClient(client.getId());
		
		assertThat(societe.getClients()).isEmpty();
		assertThat(societe.getClients()).excludes(client.getId());
	}
	
}
