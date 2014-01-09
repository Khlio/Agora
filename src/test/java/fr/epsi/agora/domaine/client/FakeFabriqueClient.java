package fr.epsi.agora.domaine.client;

import java.util.Date;

import fr.epsi.agora.domaine.societe.FakeFabriqueSociete;

public class FakeFabriqueClient {
	
	private FakeFabriqueClient() {
	}
	
	public static Client nouveau() {
		return FabriqueClient.nouveau("Saban", "JR", "a@a.com", new Date(), "Paris", "Etudiant", "Française", "1 rue du Black", "0706080910", FakeFabriqueSociete.nouveau());
	}
	
}
