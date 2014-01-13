package fr.epsi.agora.domaine.validateur;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ErreurTest {

	@Test
	public void peutAjouterUneErreur() {
		Erreur erreurs = new Erreur();
		erreurs.ajoute(Erreur.SIRET_INVALIDE);
		
		assertThat(erreurs.nombre()).isEqualTo(1);
		assertThat(erreurs.get().get(0)).isEqualTo("Le siret est invalide");
	}
	
	@Test
	public void peutDonnerLeNombreDerreurs() {
		Erreur erreurs = new Erreur();
		erreurs.ajoute("test");
		
		assertThat(erreurs.nombre()).isEqualTo(1);
	}
	
	@Test
	public void peutSavoirSilYADesErreurs() {
		Erreur erreurs = new Erreur();
		erreurs.ajoute("test");
		
		assertThat(erreurs.aDesErreurs()).isTrue();
	}
	
	@Test
	public void peutRecupererLesErreurs() {
		Erreur erreurs = new Erreur();
		erreurs.ajoute("test");
		
		assertThat(erreurs.get()).hasSize(1);
		assertThat(erreurs.get().get(0)).isEqualTo("test");
	}
	
	@Test
	public void peutRecupererLaPremiereErreur() {
		Erreur erreurs = new Erreur();
		erreurs.ajoute(Erreur.ERREUR_INCONNUE);
		
		assertThat(erreurs.premiereErreur()).isEqualTo("Une erreur inconnue s'est produite");
	}
	
}
