package fr.epsi.agora.domaine.utilisateur;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import fr.epsi.agora.domaine.societe.Societe;

public class FabriqueUtilisateur {
	
	private FabriqueUtilisateur() {
	}
	
	public static Utilisateur nouveau(String nom, String prenom, String email, String motDePasse, String adresse, String telephone, Societe societe) {
		checkNotNull(nom);
		checkNotNull(prenom);
		checkNotNull(email);
		checkNotNull(motDePasse);
		checkNotNull(adresse);
		checkNotNull(telephone);
		checkNotNull(societe);
		
		Utilisateur utilisateur = new Utilisateur(UUID.randomUUID());
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setMotDePasse(motDePasse);
		utilisateur.setAdresse(adresse);
		utilisateur.setTelephone(telephone);
		utilisateur.setConnecte(false);
		utilisateur.setSociete(societe);
		return utilisateur;
	}
	
}
