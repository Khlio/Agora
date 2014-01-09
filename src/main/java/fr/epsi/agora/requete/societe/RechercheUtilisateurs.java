package fr.epsi.agora.requete.societe;

import java.util.UUID;

import org.jongo.Jongo;

import com.google.inject.Inject;

public class RechercheUtilisateurs {

	@Inject
	public RechercheUtilisateurs(Jongo jongo) {
		this.jongo = jongo;
	}
	
	public DetailsUtilisateur detailsDe(UUID id) {
		return jongo.getCollection("utilisateur").findOne("{_id: #}", id).as(DetailsUtilisateur.class);
	}
	
	private Jongo jongo;
	
}