package fr.epsi.agora.domaine.validateur;

public class TelephoneValidateur {

	private TelephoneValidateur() {
	}
	
	public static Erreur valide(String telephone) {
		if (null != telephone && !telephone.isEmpty()) {
			if (valideUnNumeroDeTelephone(telephone)) {
				return new Erreur();
			}
		}
		return new Erreur().ajoute(Erreur.TELEPHONE_INVALIDE);
	}
	
	private static boolean valideUnNumeroDeTelephone(String telephone) {
		return aLaBonneLongueur(telephone) && aLeBonFormat(telephone);
	}
	
	private static boolean aLaBonneLongueur(String telephone) {
		return (10 == telephone.replace(" ", "").replace("-", "").replace(".", "").length());
	}
	
	private static boolean aLeBonFormat(String telephone) {
		return telephone.matches("^0[1-9]([-. ]?[0-9]{2}){4}$");
	}
	
}