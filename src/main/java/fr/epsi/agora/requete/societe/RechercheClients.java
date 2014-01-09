package fr.epsi.agora.requete.societe;

import java.util.UUID;

import org.jongo.Jongo;

import com.google.inject.Inject;

public class RechercheClients {

	@Inject
	public RechercheClients(Jongo jongo) {
		this.jongo = jongo;
	}
	
	public DetailsClient detailsDe(UUID id) {
		return jongo.getCollection("client").findOne("{_id: #}", id).as(DetailsClient.class);
	}
	
	private Jongo jongo;
	
}
