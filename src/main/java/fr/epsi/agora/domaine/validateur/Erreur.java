package fr.epsi.agora.domaine.validateur;

import java.util.List;

import com.google.common.collect.Lists;

public class Erreur {

	public static final String SIRET_INVALIDE = "Le siret est invalide";
	public static final String EMAIL_INVALIDE = "L'email est invalide";
	public static final String MOT_DE_PASSE_INVALIDE = "Le mot de passe est invalide";
	public static final String TELEPHONE_INVALIDE = "Le numéro de téléphone est invalide";
	public static final String MOT_DE_PASSE_ACTUEL_INCORRECT = "Le mot de passe actuel est invalide";
	public static final String FORMAT_NON_SUPPORTE = "Un fichier ne possède pas le bon format";
	public static final String EMAIL_EXISTANT = "Cet email est déjà utilisé";
	public static final String SIRET_EXISTANT = "Ce siret est déjà utilisé";
	
	public static final String IDENTIFIANTS_INTROUVABLE = "La combinaison email/mot de passe est introuvable";
	public static final String NON_CONNECTE = "Vous devez vous connecter pour accéder à ce contenu";
	
	public static final String ERREUR_INCONNUE = "Une erreur inconnue s'est produite";
	
	public Erreur ajoute(String erreur) {
		erreurs.add(erreur);
		return this;
	}
	
	public int nombre() {
		return erreurs.size();
	}
	
	public boolean aDesErreurs() {
		return (0 < nombre());
	}
	
	public List<String> get() {
		return erreurs;
	}
	
	public String premiereErreur() {
		return (aDesErreurs() ? erreurs.get(0) : null);
	}
	
	private List<String> erreurs = Lists.newArrayList();
	
}
