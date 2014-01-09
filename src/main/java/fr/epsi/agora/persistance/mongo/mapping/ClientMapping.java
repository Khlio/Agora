package fr.epsi.agora.persistance.mongo.mapping;

import org.mongolink.domain.mapper.AggregateMap;

import fr.epsi.agora.domaine.client.Client;

public class ClientMapping extends AggregateMap<Client> {

	public ClientMapping() {
		super(Client.class);
	}
	
	@Override
	public void map() {
		id().onProperty(element().getId()).natural();
		property().onField("nom");
		property().onField("prenom");
		property().onField("email");
		property().onField("dateDeNaissance");
		property().onField("lieuDeNaissance");
		property().onField("metier");
		property().onField("nationalite");
		property().onField("adresse");
		property().onField("telephone");
		property().onField("societe");
		collection().onField("utilisateurs");
	}

}
