package fr.epsi.agora.domaine.validateur;

public class SiretValidateur {

	private SiretValidateur() {
	}
	
	public static Erreur valide(String siret) {
		if (null != siret && !siret.isEmpty()) {
			if (valideLeSiret(siret)) {
				return new Erreur();
			}
		}
		return new Erreur().ajoute(Erreur.SIRET_INVALIDE);
	}
	
	private static boolean valideLeSiret(String siret) {
		if (17 != siret.length()) {
			return false;
		}
		return aLeBonFormat(siret);
	}
	
	private static boolean aLeBonFormat(String siret) {
		return siret.matches("^\\d{3}( |-){1}\\d{3}( |-){1}\\d{3} \\d{5}$");
	}
	
}