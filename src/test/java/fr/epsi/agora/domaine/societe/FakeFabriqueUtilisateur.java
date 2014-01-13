package fr.epsi.agora.domaine.societe;



public class FakeFabriqueUtilisateur {

	private FakeFabriqueUtilisateur() {
	}
	
	public static Utilisateur nouveau() {
		return FabriqueUtilisateur.nouveau("Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "33000", "0607080910");
	}
	
}
