package fr.epsi.agora.persistance.fake;

import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.constat.EntrepotConstats;
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
	
	@Override
	protected EntrepotConstats entrepotConstats() {
		return entrepotConstats;
	}
	
	private EntrepotSocietes entrepotSocietes = new FakeEntrepotSocietes();
	private EntrepotUtilisateurs entrepotUtilisateurs = new FakeEntrepotUtilisateurs();
	private EntrepotClients entrepotClients = new FakeEntrepotClients();
	private EntrepotConstats entrepotConstats = new FakeEntrepotConstats();
	
}
