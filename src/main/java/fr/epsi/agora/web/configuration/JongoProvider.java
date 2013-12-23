package fr.epsi.agora.web.configuration;

import java.net.UnknownHostException;

import org.jongo.Jongo;

import com.google.inject.Provider;
import com.mongodb.MongoClient;

public class JongoProvider implements Provider<Jongo> {

	@Override
	public Jongo get() {
		try {
			return new Jongo(new MongoClient().getDB("agora"));
		} catch (UnknownHostException e) {
			throw new UnknownError("Impossible de démarrer jongo");
		}
	}

}
