package fr.epsi.agora.persistance.mongo;

import org.mongolink.MongoSession;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.societe.EntrepotSocietes;
import fr.epsi.agora.domaine.societe.Societe;

public class EntrepotSocietesMongo extends EntrepotAggregatsMongo<Societe> implements EntrepotSocietes {

	public EntrepotSocietesMongo(FournisseurMongoSession fournisseur) {
		super(fournisseur);
	}

	@Override
	public Optional<Societe> get(Object id) {
		MongoSession session = fournisseur.get();
		return Optional.fromNullable(session.get(id, Societe.class));
	}

}
