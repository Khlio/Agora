package fr.epsi.agora.domaine.utilisateur;

public class FakeFabriqueUtilisateur {

	public static Utilisateur nouveau() {
		return FabriqueUtilisateur.nouveau("Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "0607080910");
	}
	
}
