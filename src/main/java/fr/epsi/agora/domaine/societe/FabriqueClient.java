package fr.epsi.agora.domaine.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

public class FabriqueClient {

	private FabriqueClient() {
	}
	
	public static Client nouveau(String nom, String prenom, String email, String dateDeNaissance, String lieuDeNaissance, String metier, String nationalite,
			String adresse, String telephone) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(dateDeNaissance);
		checkNotNull(lieuDeNaissance);
		checkNotNull(metier);
		checkNotNull(nationalite);
		checkNotNull(adresse);
		checkNotNull(telephone);
		
		Client client = new Client(UUID.randomUUID());
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setEmail(email);
		client.setDateDeNaissance(dateDeNaissance);
		client.setLieuDeNaissance(lieuDeNaissance);
		client.setMetier(metier);
		client.setNationalite(nationalite);
		client.setAdresse(adresse);
		client.setTelephone(telephone);
		return client;
	}

}
