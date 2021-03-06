package fr.epsi.agora.domaine.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

public class FabriqueUtilisateur {
	
	private FabriqueUtilisateur() {
	}
	
	public static Utilisateur nouveau(String nom, String prenom, String email, String motDePasse, String adresse, String codePostal, String telephone) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(motDePasse);
		checkNotNull(adresse);
		checkNotNull(codePostal);
		checkNotNull(telephone);
		
		Utilisateur utilisateur = new Utilisateur(UUID.randomUUID());
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setAdresse(adresse);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setTelephone(telephone);
		return utilisateur;
	}
	
}
