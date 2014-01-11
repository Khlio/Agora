package fr.epsi.agora.persistance.mongo.mapping;

import org.mongolink.domain.mapper.AggregateMap;

import fr.epsi.agora.domaine.constat.Constat;

public class ConstatMapping extends AggregateMap<Constat> {

	public ConstatMapping() {
		super(Constat.class);
	}
	
	@Override
	public void map() {
		id().onProperty(element().getId()).natural();
		property().onField("nom");
		property().onField("adresse");
		property().onField("date");
		property().onField("geolocalisation");
		property().onField("utilisateur");
		property().onField("client");
	}

}
