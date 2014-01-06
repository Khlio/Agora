package fr.epsi.agora.persistance.fake;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.client.EntrepotClients;
import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;

public class FakeEntrepots extends Entrepots {

	@Override
	protected EntrepotUtilisateurs entrepotUtilisateurs() {
		return entrepotUtilisateurs;
	}
	
	@Override
	protected EntrepotClients entrepotClients() {
		return entrepotClients;
	}
	
	private EntrepotUtilisateurs entrepotUtilisateurs = new FakeEntrepotUtilisateurs();
	private EntrepotClients entrepotClients = new FakeEntrepotClients();

}
