package fr.epsi.agora.commande;

import org.mongolink.MongoSession;

public interface FournisseurMongoSession {

	MongoSession get();
	
	void nettoie();
	
}
