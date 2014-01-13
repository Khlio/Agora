package fr.epsi.agora.requete.societe;

import java.util.List;
import java.util.UUID;

import org.jongo.Jongo;

import com.google.common.collect.Lists;
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
	
	public List<ResumeUtilisateur> tousDuneSociete(DetailsSociete societe) {
		List<ResumeUtilisateur> utilisateurs = Lists.newArrayList();
		for (String idUtilisateur : societe.getUtilisateurs()) {
			utilisateurs.add(jongo.getCollection("utilisateur").findOne("{_id: #}", UUID.fromString(idUtilisateur)).as(ResumeUtilisateur.class));
		}
		return utilisateurs;
	}
	
}
