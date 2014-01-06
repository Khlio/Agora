package fr.epsi.agora.persistance.mongo;

import org.mongolink.MongoSession;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.client.Client;
import fr.epsi.agora.domaine.client.EntrepotClients;

public class EntrepotClientsMongo extends EntrepotAggregatsMongo<Client> implements EntrepotClients {
	
	public EntrepotClientsMongo(FournisseurMongoSession fournisseur) {
		super(fournisseur);
	}

	@Override
	public Optional<Client> get(Object id) {
		MongoSession session = fournisseur.get();
		return Optional.fromNullable(session.get(id, Client.class));
	}

}
