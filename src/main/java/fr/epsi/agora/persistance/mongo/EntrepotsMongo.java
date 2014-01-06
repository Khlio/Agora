package fr.epsi.agora.persistance.mongo;

import com.google.inject.Inject;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.domaine.client.EntrepotClients;
import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;

public class EntrepotsMongo extends Entrepots {

	@Inject
	public EntrepotsMongo(FournisseurMongoSession fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	@Override
	protected EntrepotUtilisateurs entrepotUtilisateurs() {
		return new EntrepotUtilisateursMongo(fournisseur);
	}
	
	@Override
	protected EntrepotClients entrepotClients() {
		return new EntrepotClientsMongo(fournisseur);
	}
	
	private FournisseurMongoSession fournisseur;
	
}
