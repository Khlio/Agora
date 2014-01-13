package fr.epsi.agora.requete.societe;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import fr.epsi.agora.requete.Recherche;

public class RechercheSocietes extends Recherche {
	
	@Inject
	public RechercheSocietes(Jongo jongo) {
		super(jongo);
	}

	public List<ResumeSociete> toutes() {
		return Lists.newArrayList(jongo.getCollection("societe").find()
				.projection("{_id: 1, siret: 1, nom: 1}")
				.as(ResumeSociete.class));
	}
	
	public DetailsSociete detailsDe(UUID id) {
		return jongo.getCollection("societe").findOne("{_id: #}", id).as(DetailsSociete.class);
	}

	public DetailsSociete societeDeLUtilisateur(UUID idUtilisateur) {
		return jongo.getCollection("societe").findOne("{utilisateurs: #}", idUtilisateur).as(DetailsSociete.class);
	}

	public DetailsSociete societeDuClient(UUID idClient) {
		return jongo.getCollection("societe").findOne("{clients: #}", idClient).as(DetailsSociete.class);
	}
	
}
