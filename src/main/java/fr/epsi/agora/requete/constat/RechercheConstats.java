package fr.epsi.agora.requete.constat;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import fr.epsi.agora.requete.Recherche;

public class RechercheConstats extends Recherche {

	@Inject
	public RechercheConstats(Jongo jongo) {
		super(jongo);
	}
	
	public List<DetailsConstat> tousDunUtilisateur(UUID idUtilisateur) {
		return Lists.newArrayList(jongo.getCollection("constat").find("{utilisateur._id: #}", idUtilisateur).as(DetailsConstat.class));
	}
	
	public List<DetailsConstat> tousDunClient(UUID idClient) {
		return Lists.newArrayList(jongo.getCollection("constat").find("{client._id: #}", idClient).as(DetailsConstat.class));
	}
	
	public DetailsConstat detailsDe(UUID id) {
		return jongo.getCollection("constat").findOne("{_id: #}", id).as(DetailsConstat.class);
	}

}
