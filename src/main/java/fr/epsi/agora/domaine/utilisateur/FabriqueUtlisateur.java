package fr.epsi.agora.domaine.utilisateur;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

public class FabriqueUtlisateur {

	public Utilisateur nouveau(String nom, String prenom, String email, String motDePasse) {
		return nouveau(nom, prenom, email, motDePasse, "", "");
	}
	
	public Utilisateur nouveau(String nom, String prenom, String email, String motDePasse, String adresse, String telephone) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(motDePasse);
		checkNotNull(adresse);
		checkNotNull(telephone);
		
		Utilisateur utilisateur = new Utilisateur(UUID.randomUUID());
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setAdresse(adresse);
		utilisateur.setTelephone(telephone);
		utilisateur.setConnecte(false);
		return utilisateur;
	}
	
}
