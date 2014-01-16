package fr.epsi.agora.domaine.constat;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;

public class FabriqueConstat {

	private FabriqueConstat() {
	}
	
	public static Constat nouveau(String nom, String adresse1, String adresse2, String codePostal, DateTime date, String geolocalisation,
			UUID utilisateur, UUID client, List<String> audios, List<String> annexes) {
		checkNotNull(nom);
		checkNotNull(adresse1);
		checkNotNull(codePostal);
		checkNotNull(date);
		checkNotNull(geolocalisation);
		checkNotNull(utilisateur);
		checkNotNull(client);
		checkNotNull(audios);
		
		Constat constat = new Constat(UUID.randomUUID());
		constat.setNom(nom);
		constat.setAdresse1(adresse1);
		constat.setAdresse2(adresse2);
		constat.setCodePostal(codePostal);
		constat.setDate(date);
		constat.setGeolocalisation(geolocalisation);
		constat.setUtilisateur(utilisateur);
		constat.setClient(client);
		constat.setAudios(audios);
		constat.setAnnexes(annexes);
		return constat;
	}
	
}
