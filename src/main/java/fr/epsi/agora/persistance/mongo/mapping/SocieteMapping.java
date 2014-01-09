package fr.epsi.agora.persistance.mongo.mapping;

import org.mongolink.domain.mapper.AggregateMap;

import fr.epsi.agora.domaine.societe.Societe;

public class SocieteMapping extends AggregateMap<Societe> {

	public SocieteMapping() {
		super(Societe.class);
	}

	@Override
	public void map() {
		id().onProperty(element().getId()).natural();
		property().onField("siret");
		property().onField("nom");
		collection().onField("utilisateurs");
		collection().onField("clients");
	}

}
