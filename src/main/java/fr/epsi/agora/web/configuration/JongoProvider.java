package fr.epsi.agora.web.configuration;

import java.net.UnknownHostException;

import org.jongo.Jongo;

import com.google.inject.Provider;
import com.mongodb.DB;
import com.mongodb.MongoClient;

import fr.epsi.agora.Constante;

public class JongoProvider implements Provider<Jongo> {

	@Override
	public Jongo get() {
		try {
			DB db = (Constante.PROD
					? new MongoClient("dharma.mongohq.com", 10094).getDB("l63NezvVkeCikZVvYBmfw")
					: new MongoClient().getDB("agora"));
			db.authenticate("dev-agora", "dev@gora".toCharArray());
			return new Jongo(db);
		} catch (UnknownHostException e) {
			throw new UnknownError("Impossible de démarrer jongo");
		}
	}

}
