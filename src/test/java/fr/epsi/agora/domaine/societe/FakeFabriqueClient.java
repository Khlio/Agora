package fr.epsi.agora.domaine.societe;

import java.util.Date;

public class FakeFabriqueClient {
	
	private FakeFabriqueClient() {
	}
	
	public static Client nouveau() {
		return FabriqueClient.nouveau("Saban", "JR", "a@a.com", new Date(), "Paris", "Etudiant", "Française", "1 rue du Black", "0706080910");
	}
	
}
