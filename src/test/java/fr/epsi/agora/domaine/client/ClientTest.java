package fr.epsi.agora.domaine.client;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.epsi.agora.domaine.societe.Societe;
import fr.epsi.agora.domaine.utilisateur.FakeFabriqueUtilisateur;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

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
		
		assertThat(client.getDateDeNaissance()).isNotNull();
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
	
	@Test
	public void peutDonnerUneSociete() {
		Client client = FakeFabriqueClient.nouveau();
		
		assertThat(client.getSociete()).isNotNull();
		assertThat(client.getSociete()).isInstanceOf(Societe.class);
	}
	
	@Test
	public void peutAjouterUnUtilisateur() {
		Client client = FakeFabriqueClient.nouveau();
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		
		Utilisateur utilisateurAjoute = client.ajouteUtilisateur(utilisateur);
		
		assertThat(utilisateurAjoute).isNotNull();
		assertThat(utilisateurAjoute).isEqualTo(utilisateur);
		assertThat(client.getUtilisateurs()).isNotNull();
		assertThat(client.getUtilisateurs()).isNotEmpty();
		assertThat(client.getUtilisateurs()).contains(utilisateurAjoute);
	}
	
	@Test
	public void peutSupprimerUnUtilisateur() {
		Client client = FakeFabriqueClient.nouveau();
		Utilisateur utilisateur = FakeFabriqueUtilisateur.nouveau();
		client.ajouteUtilisateur(utilisateur);
		
		client.supprimeUtilisateur(utilisateur);
		
		assertThat(client.getUtilisateurs()).isEmpty();
		assertThat(client.getUtilisateurs()).excludes(utilisateur);
	}
	
}
