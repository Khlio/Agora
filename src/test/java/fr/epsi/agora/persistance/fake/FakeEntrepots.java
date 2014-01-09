package fr.epsi.agora.persistance.fake;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.societe.EntrepotClients;
import fr.epsi.agora.domaine.societe.EntrepotSocietes;
import fr.epsi.agora.domaine.societe.EntrepotUtilisateurs;

public class FakeEntrepots extends Entrepots {

	@Override
	protected EntrepotSocietes entrepotSocietes() {
		return entrepotSocietes;
	}
	
	@Override
	protected EntrepotUtilisateurs entrepotUtilisateurs() {
		return entrepotUtilisateurs;
	}
	
	@Override
	protected EntrepotClients entrepotClients() {
		return entrepotClients;
	}
	
	private EntrepotSocietes entrepotSocietes = new FakeEntrepotSocietes();
	private EntrepotUtilisateurs entrepotUtilisateurs = new FakeEntrepotUtilisateurs();
	private EntrepotClients entrepotClients = new FakeEntrepotClients();
	
}
