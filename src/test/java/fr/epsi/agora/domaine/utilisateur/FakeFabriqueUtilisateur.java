package fr.epsi.agora.domaine.utilisateur;

import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;

public class FakeFabriqueUtilisateur {

	private FakeFabriqueUtilisateur() {
	}
	
	public static Utilisateur nouveau() {
		return FabriqueUtilisateur.nouveau("Levacher", "Vincent", "a@a.com", "pass", "1 rue Test", "0607080910", FakeFabriqueSociete.nouveau());
	}
	
}
