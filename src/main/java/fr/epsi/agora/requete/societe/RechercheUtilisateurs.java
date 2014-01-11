package fr.epsi.agora.requete.societe;

import java.util.UUID;

import org.jongo.Jongo;

import com.google.inject.Inject;

import fr.epsi.agora.requete.Recherche;

public class RechercheUtilisateurs extends Recherche {

	@Inject
	public RechercheUtilisateurs(Jongo jongo) {
		super(jongo);
	}
	
	public DetailsUtilisateur detailsDe(UUID id) {
		return jongo.getCollection("utilisateur").findOne("{_id: #}", id).as(DetailsUtilisateur.class);
	}
	
	public DetailsUtilisateur detailsDe(String email, String motDePasse) {
		return jongo.getCollection("utilisateur").findOne("{email: #, motDePasse: #}", email, motDePasse).as(DetailsUtilisateur.class);
	}
	
}
