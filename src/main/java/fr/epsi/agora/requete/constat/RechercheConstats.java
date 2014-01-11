package fr.epsi.agora.requete.constat;

import java.util.UUID;

import org.jongo.Jongo;

import com.google.inject.Inject;

import fr.epsi.agora.requete.Recherche;

public class RechercheConstats extends Recherche {

	@Inject
	public RechercheConstats(Jongo jongo) {
		super(jongo);
	}
	
	public DetailsConstat detailsDe(UUID id) {
		return jongo.getCollection("constat").findOne("{_id: #}", id).as(DetailsConstat.class);
	}

}
