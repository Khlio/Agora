package fr.epsi.agora.requete.utilisateur;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class RechercheUtilisateurs {

	@Inject
	public RechercheUtilisateurs(Jongo jongo) {
		this.jongo = jongo;
	}
	
	public List<DetailsUtilisateur> tous() {
		return Lists.newArrayList(jongo.getCollection("utilisateur").find()
				.projection("{_id: 1, nom: 1, prenom: 1, email: 1, motDePasse: 1, adresse: 1, telephone: 1, derniereConnexion: 1, connecte: 1}").as(DetailsUtilisateur.class));
	}
	
	public DetailsUtilisateur detailsDe(UUID id) {
		return jongo.getCollection("utilisateur").findOne("{_id: #}", id).as(DetailsUtilisateur.class);
	}
	
	private Jongo jongo;
	
}
