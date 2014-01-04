package fr.epsi.agora.web.ressource.utilisateur;

import java.util.UUID;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.SuppressionUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;

public class UtilisateurRessource extends ServerResource {

	@Inject
	public UtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() throws ResourceException {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		utilisateur = recherche.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		return new JacksonRepresentation<>(utilisateur);
	}
	
	@Delete
	public void supprime() {
		SuppressionUtilisateurMessage commande = new SuppressionUtilisateurMessage(UUID.fromString(utilisateur.getId()));
		busCommande.envoie(commande);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private DetailsUtilisateur utilisateur;
	
}