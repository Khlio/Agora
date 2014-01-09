package fr.epsi.agora.domaine;

import fr.epsi.agora.domaine.societe.EntrepotClients;
import fr.epsi.agora.domaine.societe.EntrepotSocietes;
import fr.epsi.agora.domaine.societe.EntrepotUtilisateurs;

public abstract class Entrepots {

	public static EntrepotSocietes societes() {
		return instance.entrepotSocietes();
	}
	
	public static EntrepotUtilisateurs utilisateurs() {
		return instance.entrepotUtilisateurs();
	}
	
	public static EntrepotClients clients() {
		return instance.entrepotClients();
	}
	
	protected abstract EntrepotSocietes entrepotSocietes();
	protected abstract EntrepotUtilisateurs entrepotUtilisateurs();
	protected abstract EntrepotClients entrepotClients();
	
	public static void setInstance(Entrepots instance) {
		Entrepots.instance = instance;
	}
	
	private static Entrepots instance;
	
}
