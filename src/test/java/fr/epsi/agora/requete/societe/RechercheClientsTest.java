package fr.epsi.agora.requete.societe;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.jongo.Jongo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;

public class RechercheClientsTest {

	@Before
	public void setUp() {
		fongo = new Fongo("test");
		jongo = new Jongo(fongo.getDB("agora"));
	}
	
	@After
	public void tearDown() {
		fongo.dropDatabase("agora");
	}
	
	@Test
	public void peutRecupererUnClient() {
		UUID idClient = UUID.randomUUID();
		jongo.getCollection("client").insert("{_id: #, nom: 'Saban', prenom: 'JR', email: 'a@a.com', dateDeNaissance: '01/01/1991', "
				+ "lieuDeNaissance: 'Paris', metier: 'Etudiant', nationalite: 'Française', adresse1: '1 rue du Black', codePostal: '33000', "
				+ "telephonePortable: '0706080910', telephoneFixe: '0506070809'}", idClient);
		RechercheClients recherche = new RechercheClients(jongo);
		
		DetailsClient details = recherche.detailsDe(idClient);
		
		assertThat(details).isNotNull();
		assertThat(details.getNom()).isEqualTo("Saban");
		assertThat(details.getPrenom()).isEqualTo("JR");
		assertThat(details.getEmail()).isEqualTo("a@a.com");
		assertThat(details.getDateDeNaissance()).isEqualTo("01/01/1991");
		assertThat(details.getLieuDeNaissance()).isEqualTo("Paris");
		assertThat(details.getMetier()).isEqualTo("Etudiant");
		assertThat(details.getNationalite()).isEqualTo("Française");
		assertThat(details.getAdresse1()).isEqualTo("1 rue du Black");
		assertThat(details.getCodePostal()).isEqualTo("33000");
		assertThat(details.getTelephonePortable()).isEqualTo("0706080910");
		assertThat(details.getTelephoneFixe()).isEqualTo("0506070809");
	}
	
	private Fongo fongo;
	private Jongo jongo;
	
}
