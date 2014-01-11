package fr.epsi.agora.domaine.constat;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import fr.epsi.agora.domaine.societe.Client;
import fr.epsi.agora.domaine.societe.Utilisateur;

public class FabriqueConstat {

	private FabriqueConstat() {
	}
	
	public static Constat nouveau(String nom, String date, String geolocalisation, Utilisateur utilisateur, Client client) {
		checkNotNull(nom);
		checkNotNull(date);
		checkNotNull(geolocalisation);
		checkNotNull(utilisateur);
		checkNotNull(client);
		
		Constat constat = new Constat(UUID.randomUUID());
		constat.setNom(nom);
		constat.setDate(date);
		constat.setGeolocalisation(geolocalisation);
		constat.setUtilisateur(utilisateur);
		constat.setClient(client);
		return constat;
	}
	
}
