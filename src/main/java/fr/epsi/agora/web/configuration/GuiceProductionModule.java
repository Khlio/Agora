package fr.epsi.agora.web.configuration;

import org.jongo.Jongo;
import org.mongolink.MongoSessionManager;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.persistance.mongo.EntrepotsMongo;
import fr.epsi.agora.persistance.mongo.FournisseurMongoSessionParThread;

public class GuiceProductionModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(MongoSessionManager.class).toProvider(MongoSessionManagerProvider.class).in(Singleton.class);
		bind(FournisseurMongoSession.class).to(FournisseurMongoSessionParThread.class).in(Singleton.class);
		bind(Jongo.class).toProvider(JongoProvider.class).in(Singleton.class);
		bind(Entrepots.class).to(EntrepotsMongo.class);
		bind(BusCommande.class).in(Singleton.class);
	}
	
}
