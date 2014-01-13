package fr.epsi.agora.requete.societe;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import fr.epsi.agora.requete.Recherche;

public class RechercheClients extends Recherche {
	
	@Inject
	public RechercheClients(Jongo jongo) {
		super(jongo);
	}

	public DetailsClient detailsDe(UUID id) {
		return jongo.getCollection("client").findOne("{_id: #}", id).as(DetailsClient.class);
	}
	
	public List<ResumeClient> tousDuneSociete(DetailsSociete societe) {
		List<ResumeClient> clients = Lists.newArrayList();
		for (String idClient : societe.getClients()) {
			clients.add(jongo.getCollection("client").findOne("{_id: #}", UUID.fromString(idClient)).as(ResumeClient.class));
		}
		return clients;
	}
	
}
