package fr.epsi.agora.web.configuration;

import java.net.UnknownHostException;

import org.jongo.Jongo;

import com.google.inject.Provider;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class JongoProvider implements Provider<Jongo> {

	@Override
	public Jongo get() {
		try {
			DB db = new MongoClient().getDB("agora");
			/*DB db = new MongoClient("dharma.mongohq.com", 10094).getDB("l63NezvVkeCikZVvYBmfw");
			db.authenticate("dev-agora", "dev@gora".toCharArray());*/
			return new Jongo(db);
		} catch (UnknownHostException e) {
			throw new UnknownError("Impossible de démarrer jongo");
		}
	}

}
