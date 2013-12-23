package fr.epsi.agora.domaine;

import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;

public abstract class Entrepots {

	public static EntrepotUtilisateurs utilisateurs() {
		return instance.entrepotUtilisateurs();
	}
	
	protected abstract EntrepotUtilisateurs entrepotUtilisateurs();
	
	public static void setInstance(Entrepots instance) {
		Entrepots.instance = instance;
	}
	
	private static Entrepots instance;
	
}
