package fr.epsi.agora.domaine;

import fr.epsi.agora.domaine.client.EntrepotClients;
import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;

public abstract class Entrepots {

	public static EntrepotUtilisateurs utilisateurs() {
		return instance.entrepotUtilisateurs();
	}
	
	public static EntrepotClients clients() {
		return instance.entrepotClients();
	}
	
	protected abstract EntrepotUtilisateurs entrepotUtilisateurs();
	
	protected abstract EntrepotClients entrepotClients();
	
	public static void setInstance(Entrepots instance) {
		Entrepots.instance = instance;
	}
	
	private static Entrepots instance;
	
}
