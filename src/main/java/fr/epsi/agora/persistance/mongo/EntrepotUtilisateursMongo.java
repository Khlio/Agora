package fr.epsi.agora.persistance.mongo;

import org.mongolink.MongoSession;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.utilisateur.EntrepotUtilisateurs;
import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class EntrepotUtilisateursMongo extends EntrepotAggregatsMongo<Utilisateur> implements EntrepotUtilisateurs {

	public EntrepotUtilisateursMongo(FournisseurMongoSession fournisseur) {
		super(fournisseur);
	}
	
	@Override
	public Optional<Utilisateur> get(Object id) {
		MongoSession session = fournisseur.get();
		return Optional.fromNullable(session.get(id, Utilisateur.class));
	}
	
}
