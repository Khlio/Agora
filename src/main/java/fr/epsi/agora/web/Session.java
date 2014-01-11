package fr.epsi.agora.web;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import org.joda.time.DateTime;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

import fr.epsi.agora.Constante;

public class Session {
	
	private Session(String nom, DateTime debutSession) {
		this.nom = nom;
		this.debutSession = debutSession;
	}
	
	public static Session ajoute(String nom) {
		return ajoute(nom, DateTime.now());
	}
	
	public static Session ajoute(String nom, DateTime debutSession) {
		checkNotNull(nom);
		checkNotNull(debutSession);
		
		sessions.put(nom, debutSession);
		Session session = new Session(nom, debutSession);
		return session;
	}
	
	public static void supprime(String nom) {
		sessions.remove(nom);
	}
	
	public static Optional<Session> get(String nom) {
		checkNotNull(nom);
		
		Session session = null;
		if (sessions.containsKey(nom)) {
			DateTime debutSession = sessions.get(nom);
			if (estUneSessionValide(debutSession)) {
				session = new Session(nom, debutSession);
			} else {
				supprime(nom);
			}
		}
		return Optional.fromNullable(session);
	}
	
	private static boolean estUneSessionValide(DateTime dateSession) {
		return dateSession.plusHours(Constante.DELAI_SESSION).isAfterNow();
	}
	
	public String getNom() {
		return nom;
	}

	public DateTime getDebutSession() {
		return debutSession;
	}
	
	private static final Map<String, DateTime> sessions = Maps.newConcurrentMap();
	private String nom;
	private DateTime debutSession;
	
}
