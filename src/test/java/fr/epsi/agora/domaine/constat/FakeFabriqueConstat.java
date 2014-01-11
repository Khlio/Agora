package fr.epsi.agora.domaine.constat;

import fr.epsi.agora.domaine.societe.FakeFabriqueClient;
import fr.epsi.agora.domaine.societe.FakeFabriqueUtilisateur;

public class FakeFabriqueConstat {

	private FakeFabriqueConstat() {
	}
	
	public static Constat nouveau() {
		return FabriqueConstat.nouveau("Tout cassé", "01/01/2014", "", FakeFabriqueUtilisateur.nouveau(), FakeFabriqueClient.nouveau());
	}
	
}
