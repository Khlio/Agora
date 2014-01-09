package fr.epsi.agora.requete.societe;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class RechercheSocietes {

	@Inject
	public RechercheSocietes(Jongo jongo) {
		this.jongo = jongo;
	}
	
	public List<ResumeSociete> toutes() {
		return Lists.newArrayList(jongo.getCollection("societe").find()
				.projection("{_id: 1, siret: 1, nom: 1}")
				.as(ResumeSociete.class));
	}
	
	public DetailsSociete detailsDe(UUID id) {
		return jongo.getCollection("societe").findOne("{_id: #}", id).as(DetailsSociete.class);
	}
	
	private Jongo jongo;
	
}
