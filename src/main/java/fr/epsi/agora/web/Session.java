package fr.epsi.agora.web;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;
import java.util.UUID;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class Session {
	
	private Session(String nom, UUID valeur) {
		this.nom = nom;
		this.valeur = valeur;
	}
	
	public static Session ajoute(String nom, UUID valeur) {
		checkNotNull(nom);
		checkNotNull(valeur);
		
		sessions.put(nom, valeur);
		Session session = new Session(nom, valeur);
		return session;
	}
	
	public static void supprime(String nom) {
		sessions.remove(nom);
	}
	
	public static Optional<Session> get(String nom) {
		checkNotNull(nom);
		
		Session session = null;
		if (sessions.containsKey(nom)) {
			session = new Session(nom, sessions.get(nom));
		}
		return Optional.fromNullable(session);
	}
	
	public String getNom() {
		return nom;
	}

	public UUID getValeur() {
		return valeur;
	}
	
	private static final Map<String, UUID> sessions = Maps.newConcurrentMap();
	private final String nom;
	private final UUID valeur;
	
}
