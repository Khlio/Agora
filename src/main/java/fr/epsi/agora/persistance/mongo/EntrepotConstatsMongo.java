package fr.epsi.agora.persistance.mongo;

import org.mongolink.MongoSession;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.EntrepotConstats;

public class EntrepotConstatsMongo extends EntrepotAggregatsMongo<Constat> implements EntrepotConstats {

	public EntrepotConstatsMongo(FournisseurMongoSession fournisseur) {
		super(fournisseur);
	}
	
	@Override
	public Optional<Constat> get(Object id) {
		MongoSession session = fournisseur.get();
		return Optional.fromNullable(session.get(id, Constat.class));
	}

}
