package fr.epsi.agora.web.configuration;

import org.mongolink.MongoSessionManager;
import org.mongolink.Settings;
import org.mongolink.domain.UpdateStrategies;
import org.mongolink.domain.mapper.ContextBuilder;

import com.google.inject.Provider;

public class MongoSessionManagerProvider implements Provider<MongoSessionManager> {

	@Override
	public MongoSessionManager get() {
		return MongoSessionManager.create(new ContextBuilder("fr.epsi.agora.persistance.mongo.mapping"),
				Settings.defaultInstance()
					/*.withHost("dharma.mongohq.com")
					.withPort(10094)
					.withAuthentication("dev-agora", "dev@gora")
					.withDbName("l63NezvVkeCikZVvYBmfw")*/
					.withDbName("agora")
					.withDefaultUpdateStrategy(UpdateStrategies.DIFF));
	}

}
