package fr.epsi.agora.web;

import static org.fest.assertions.Assertions.assertThat;

import java.util.UUID;

import org.junit.Test;

public class SessionTest {

	@Test
	public void peutAjouterUneSession() {
		UUID id = UUID.randomUUID();
		Session session = Session.ajoute("utilisateur", id);
		
		assertThat(session).isNotNull();
		assertThat(session.getNom()).isEqualTo("utilisateur");
		assertThat(session.getValeur()).isEqualTo(id);
	}
	
	@Test
	public void peutSupprimerUneSession() {
		Session.ajoute("utilisateur", UUID.randomUUID());
		
		Session.supprime("utilisateur");
		
		assertThat(Session.get("utilisateur").isPresent()).isFalse();
	}
	
	@Test
	public void peutRecupererUneSessionAvecSonNom() {
		Session session = Session.ajoute("utilisateur", UUID.randomUUID());
		
		Session sessionRecuperee = Session.get("utilisateur").orNull();
		
		assertThat(sessionRecuperee).isNotNull();
		assertThat(sessionRecuperee.getNom()).isEqualTo(session.getNom());
		assertThat(sessionRecuperee.getValeur()).isEqualTo(session.getValeur());
	}
	
}
