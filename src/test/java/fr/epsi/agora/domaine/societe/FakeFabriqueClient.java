package fr.epsi.agora.domaine.societe;


public class FakeFabriqueClient {
	
	private FakeFabriqueClient() {
	}
	
	public static Client nouveau() {
		return FabriqueClient.nouveau("Saban", "JR", "a@a.com", "01/01/1991", "Paris", "Etudiant", "Française", "1 rue du Black", "0706080910");
	}
	
}
