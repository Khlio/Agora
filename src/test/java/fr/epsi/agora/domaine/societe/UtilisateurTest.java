package fr.epsi.agora.domaine.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;

import fr.epsi.agora.domaine.societe.Utilisateur;

public class UtilisateurTest {

	@Test
	public void peutDonnerUnIdentifiant() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur).isNotNull();
		assertThat(utilisateur.getId()).isNotNull();
	}
	
	@Test
	public void peutDonnerUnNom() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getNom()).isEqualTo("Levacher");
	}
	
	@Test
	public void peutDonnerUnPrenom() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getPrenom()).isEqualTo("Vincent");
	}
	
	@Test
	public void peutDonnerUnEmail() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getEmail()).isEqualTo("a@a.com");
	}
	
	@Test
	public void peutDonnerUnMotDePasse() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getMotDePasse()).isEqualTo("pass");
	}
	
	@Test
	public void peutDonnerUneAdresse() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getAdresse()).isEqualTo("1 rue Test");
	}
	
	@Test
	public void peutDonnerUnTelephone() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.getTelephone()).isEqualTo("0607080910");
	}
	
	@Test
	public void peutDonnerUneDerniereConnexion() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		utilisateur.setDerniereConnexion(new Date());
		
		assertThat(utilisateur.getDerniereConnexion()).isNotNull();
	}
	
	@Test
	public void peutDonnerSilEstConnecte() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		utilisateur.setConnecte(true);
		
		assertThat(utilisateur.isConnecte()).isTrue();
	}
	
	@Test
	public void peutDonnerSilNestPasConnecte() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		assertThat(utilisateur.isConnecte()).isFalse();
	}
	
	@Test
	public void peutAjouterUnClient() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		Client client = FakeFabriqueClient.nouveau();
		
		Client clientAjoute = utilisateur.ajouteClient(client);
		
		assertThat(clientAjoute).isNotNull();
		assertThat(clientAjoute).isEqualTo(client);
		assertThat(utilisateur.getClients()).isNotNull();
		assertThat(utilisateur.getClients()).isNotEmpty();
		assertThat(utilisateur.getClients()).contains(clientAjoute);
	}
	
	@Test
	public void peutSupprimerUnClient() {
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		Client client = FakeFabriqueClient.nouveau();
		utilisateur.ajouteClient(client);
		
		utilisateur.supprimeClient(client);
		
		assertThat(utilisateur.getClients()).isEmpty();
		assertThat(utilisateur.getClients()).excludes(client);
	}
	
}
