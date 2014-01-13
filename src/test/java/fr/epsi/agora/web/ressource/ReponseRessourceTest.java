package fr.epsi.agora.web.ressource;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.StringRepresentation;

import fr.epsi.agora.domaine.validateur.Erreur;

public class ReponseRessourceTest {

	@Test
	public void peutDonnerUneReponseOK() {
		StringRepresentation reponseOK = new StringRepresentation("");
		
		assertThat(ReponseRessource.OK).isEqualTo(reponseOK);
	}
	
	@Test
	public void peutDonnerUneErreurInconnue() {
		StringRepresentation erreur = new StringRepresentation(Erreur.ERREUR_INCONNUE);
		
		assertThat(ReponseRessource.ERREUR).isEqualTo(erreur);
	}
	
	@Test
	public void peutDonnerUneReponseDUtilisateurNonConnecte() {
		StringRepresentation nonConnecte = new StringRepresentation(Erreur.NON_CONNECTE);
		
		assertThat(ReponseRessource.NON_CONNECTE).isEqualTo(nonConnecte);
	}
	
	@Test
	public void peutDonnerUneReponseAvecUnMessage() {
		StringRepresentation test = new StringRepresentation("test");
		
		assertThat(ReponseRessource.get("test")).isEqualTo(test);
	}
	
	@Test
	public void peutDonnerUneReponseJSONAvecUnMessage() {
		JacksonRepresentation<String> test = new JacksonRepresentation<>("test");
		
		assertThat(ReponseRessource.json("test")).isEqualTo(test);
	}
	
}
