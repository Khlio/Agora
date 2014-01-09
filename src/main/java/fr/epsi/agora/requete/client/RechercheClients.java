package fr.epsi.agora.requete.client;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class RechercheClients {

	@Inject
	public RechercheClients(Jongo jongo) {
		this.jongo = jongo;
	}
	
	public List<ResumeClient> tous() {
		return Lists.newArrayList(jongo.getCollection("client").find()
				.projection("{_id: 1, nom: 1, prenom: 1}")
				.as(ResumeClient.class));
	}
	
	public DetailsClient detailsDe(UUID id) {
		return jongo.getCollection("client").findOne("{_id: #}", id).as(DetailsClient.class);
	}
	
	private Jongo jongo;
	
}
