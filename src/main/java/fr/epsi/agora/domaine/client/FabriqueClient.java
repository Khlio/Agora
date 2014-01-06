package fr.epsi.agora.domaine.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.UUID;

public class FabriqueClient {

	public static Client nouveau(String nom, String prenom, String email, Date dateNaissance, String lieuNaissance, String metier, String nationalite,
			String adresse, String telephone) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(dateNaissance);
		checkNotNull(lieuNaissance);
		checkNotNull(metier);
		checkNotNull(nationalite);
		checkNotNull(adresse);
		checkNotNull(telephone);
		
		Client client = new Client(UUID.randomUUID());
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setEmail(email);
		client.setDateNaissance(dateNaissance);
		client.setLieuNaissance(lieuNaissance);
		client.setMetier(metier);
		client.setNationalite(nationalite);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		return client;
	}

}
