package fr.epsi.agora.web;

import static org.fest.assertions.Assertions.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

import fr.epsi.agora.Constante;

public class SessionTest {

	@Test
	public void peutAjouterUneSession() {
		Session session = Session.ajoute("utilisateur");
		
		assertThat(session).isNotNull();
		assertThat(session.getNom()).isEqualTo("utilisateur");
		assertThat(session.getDebutSession()).isNotNull();
	}
	
	@Test
	public void peutSupprimerUneSession() {
		Session.ajoute("utilisateur");
		
		Session.supprime("utilisateur");
		
		assertThat(Session.get("utilisateur").isPresent()).isFalse();
	}
	
	@Test
	public void peutRecupererUneSessionAvecSonNom() {
		Session session = Session.ajoute("utilisateur");
		
		Session sessionRecuperee = Session.get("utilisateur").orNull();
		
		assertThat(sessionRecuperee).isNotNull();
		assertThat(sessionRecuperee.getNom()).isEqualTo(session.getNom());
		assertThat(sessionRecuperee.getDebutSession()).isEqualTo(session.getDebutSession());
	}
	
	@Test
	public void nePeutPasRecupererUneSessionExpiree() {
		Session.ajoute("utilisateur", DateTime.now().minusHours(Constante.DELAI_SESSION));
		
		Session sessionRecuperee = Session.get("utilisateur").orNull();
		
		assertThat(sessionRecuperee).isNull();
	}
	
}
