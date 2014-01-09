package fr.epsi.agora.web.ressource.utilisateur;

import java.io.IOException;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.CreationUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;

public class UtilisateursRessource extends ServerResource {

	@Inject
	public UtilisateursRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(recherche.tous());
	}
	
	@Post("json")
	public void cree(Representation representation) throws IOException {
		JacksonRepresentation<DetailsUtilisateur> json = new JacksonRepresentation<DetailsUtilisateur>(representation, DetailsUtilisateur.class);
		DetailsUtilisateur details = json.getObject();
		CreationUtilisateurMessage commande = new CreationUtilisateurMessage(details.getNom(), details.getPrenom(), details.getEmail(), details.getMotDePasse(),
				details.getAdresse(), details.getTelephone(), details.getSociete());
		busCommande.envoie(commande);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	
}
