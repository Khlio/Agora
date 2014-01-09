package fr.epsi.agora.persistance.mongo.mapping;

import org.mongolink.domain.mapper.AggregateMap;

import fr.epsi.agora.domaine.utilisateur.Utilisateur;

public class UtilisateurMapping extends AggregateMap<Utilisateur> {

	public UtilisateurMapping() {
		super(Utilisateur.class);
	}
	
	@Override
	public void map() {
		id().onProperty(element().getId()).natural();
		property().onField("nom");
		property().onField("prenom");
		property().onField("email");
		property().onField("motDePasse");
		property().onField("adresse");
		property().onField("telephone");
		property().onField("derniereConnexion");
		property().onField("connecte");
		property().onField("societe");
		collection().onField("clients");
	}

}
