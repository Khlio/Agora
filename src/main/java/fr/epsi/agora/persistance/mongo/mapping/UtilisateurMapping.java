package fr.epsi.agora.persistance.mongo.mapping;

import org.mongolink.domain.mapper.AggregateMap;

import fr.epsi.agora.domaine.societe.Utilisateur;

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
		property().onField("codePostal");
		property().onField("telephone");
	}

}
