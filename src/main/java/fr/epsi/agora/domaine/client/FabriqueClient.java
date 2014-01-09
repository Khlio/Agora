package fr.epsi.agora.domaine.client;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;
import java.util.UUID;

import fr.epsi.agora.domaine.societe.Societe;

public class FabriqueClient {

	private FabriqueClient() {
	}
	
	public static Client nouveau(String nom, String prenom, String email, Date dateNaissance, String lieuNaissance, String metier, String nationalite,
			String adresse, String telephone, Societe societe) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(dateNaissance);
		checkNotNull(lieuNaissance);
		checkNotNull(metier);
		checkNotNull(nationalite);
		checkNotNull(adresse);
		checkNotNull(telephone);
		checkNotNull(societe);
		
		Client client = new Client(UUID.randomUUID());
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setEmail(email);
		client.setDateDeNaissance(dateNaissance);
		client.setLieuDeNaissance(lieuNaissance);
		client.setMetier(metier);
		client.setNationalite(nationalite);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		client.setSociete(societe);
		return client;
	}

}
