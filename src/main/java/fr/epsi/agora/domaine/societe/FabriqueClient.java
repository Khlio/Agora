package fr.epsi.agora.domaine.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

public class FabriqueClient {

	private FabriqueClient() {
	}
	
	public static Client nouveau(String nom, String prenom, String email, String dateDeNaissance, String lieuDeNaissance, String metier, String nationalite,
			String adresse1, String adresse2, String codePostal, String telephonePortable, String telephoneFixe) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(dateDeNaissance);
		checkNotNull(lieuDeNaissance);
		checkNotNull(metier);
		checkNotNull(nationalite);
		checkNotNull(adresse1);
		checkNotNull(codePostal);
		checkNotNull(telephonePortable);
		
		Client client = new Client(UUID.randomUUID());
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setEmail(email);
		client.setDateDeNaissance(dateDeNaissance);
		client.setLieuDeNaissance(lieuDeNaissance);
		client.setMetier(metier);
		client.setNationalite(nationalite);
		client.setAdresse1(adresse1);
		client.setAdresse2(adresse2);
		client.setCodePostal(codePostal);
		client.setTelephonePortable(telephonePortable);
		client.setTelephoneFixe(telephoneFixe);
		return client;
	}

}
