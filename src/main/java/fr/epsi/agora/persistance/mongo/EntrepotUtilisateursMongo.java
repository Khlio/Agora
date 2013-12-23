package fr.epsi.agora.persistance.mongo;

import org.mongolink.MongoSession;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class EntrepotUtilisateursMongo implements EntrepotUtilisateurs {

	public EntrepotUtilisateursMongo(FournisseurMongoSession fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	@Override
	public Optional<Utilisateur> get(Object id) {
		MongoSession session = fournisseur.get();
		return Optional.fromNullable(session.get(id, Utilisateur.class));
	}

	@Override
	public Utilisateur ajoute(Utilisateur aggregat) {
		fournisseur.get().save(aggregat);
		return aggregat;
	}

	@Override
	public void supprime(Utilisateur aggregat) {
		fournisseur.get().delete(aggregat);
	}
	
	private FournisseurMongoSession fournisseur;
	
}
