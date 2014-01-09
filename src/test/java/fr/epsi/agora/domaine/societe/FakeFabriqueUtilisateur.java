package fr.epsi.agora.domaine.societe;

import fr.epsi.agora.domaine.societe.FabriqueUtilisateur;
import fr.epsi.agora.domaine.societe.Utilisateur;


public class FakeFabriqueUtilisateur {

	private FakeFabriqueUtilisateur() {
	}
	
	public static Utilisateur nouveau() {
		return FabriqueUtilisateur.nouveau("Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "0607080910");
	}
	
}
