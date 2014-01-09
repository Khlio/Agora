package fr.epsi.agora.domaine.societe;

public class FakeFabriqueSociete {

	private FakeFabriqueSociete() {
	}
	
	public static Societe nouveau() {
		return FabriqueSociete.nouveau("552-120-222 00013", "Société générale");
	}
	
}
