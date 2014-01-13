package fr.epsi.agora.domaine.validateur;

public class EmailValidateur {

	private EmailValidateur() {
	}
	
	public static Erreur valide(String email) {
		if (null != email && !email.isEmpty()) {
			if (valideLEmail(email)) {
				return new Erreur();
			}
		}
		return new Erreur().ajoute(Erreur.EMAIL_INVALIDE);
	}
	
	private static boolean valideLEmail(String email) {
		return email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
	}
	
}