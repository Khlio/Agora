package fr.epsi.agora.requete.societe;

import java.util.UUID;

import org.jongo.Jongo;

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
	
}
