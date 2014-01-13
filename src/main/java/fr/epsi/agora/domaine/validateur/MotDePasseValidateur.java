package fr.epsi.agora.domaine.validateur;

public class MotDePasseValidateur {

	private MotDePasseValidateur() {
	}
	
	public static Erreur valide(String motDePasse) {
		if (null != motDePasse && !motDePasse.isEmpty()) {
			if (valideLeMotDePasse(motDePasse)) {
				return new Erreur();
			}
		}
		return new Erreur().ajoute(Erreur.MOT_DE_PASSE_INVALIDE);
	}
	
	private static boolean valideLeMotDePasse(String motDePasse) {
		return estAssezLong(motDePasse) && estComplexe(motDePasse);
	}
	
	private static boolean estAssezLong(String motDePasse) {
		return (8 <= motDePasse.length());
	}
	
	private static boolean estComplexe(String motDePasse) {
		return motDePasse.matches("^.*(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).*$");
	}
	
}