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
		
		Utilisateur utilisateurAjoute = societe.ajouteUtilisateur(utilisateur);
		
		assertThat(utilisateurAjoute).isNotNull();
		assertThat(utilisateurAjoute).isEqualTo(utilisateurAjoute);
		assertThat(societe.getUtilisateurs()).isNotNull();
		assertThat(societe.getUtilisateurs()).isNotEmpty();
		assertThat(societe.getUtilisateurs()).contains(utilisateurAjoute);
	}
	
	@Test
	public void peutSupprimerUnUtilisateur() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		societe.ajouteUtilisateur(utilisateur);
		
		societe.supprimeUtilisateur(utilisateur);
		
		assertThat(societe.getUtilisateurs()).isEmpty();
		assertThat(societe.getUtilisateurs()).excludes(utilisateur);
	}
	
	@Test
	public void peutAjouterUnClient() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Client client = FakeFabriqueClient.nouveau();
		
		Client clientAjoute = societe.ajouteClient(client);
		
		assertThat(clientAjoute).isNotNull();
		assertThat(clientAjoute).isEqualTo(clientAjoute);
		assertThat(societe.getClients()).isNotNull();
		assertThat(societe.getClients()).isNotEmpty();
		assertThat(societe.getClients()).contains(clientAjoute);
	}
	
	@Test
	public void peutSupprimerUnClient() {
		Societe societe = FakeFabriqueSociete.nouveau();
		Client client = FakeFabriqueClient.nouveau();
		societe.ajouteClient(client);
		
		societe.supprimeClient(client);
		
		assertThat(societe.getClients()).isEmpty();
		assertThat(societe.getClients()).excludes(client);
	}
	
}
