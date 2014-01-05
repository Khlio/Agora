package fr.epsi.agora.web.ressource.utilisateur;

import java.util.UUID;

import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.ConnexionUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.DetailsUtilisateur;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;

public class ConnexionUtilisateurRessource extends ServerResource {

	@Inject
	public ConnexionUtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() throws ResourceException {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		utilisateur = recherche.detailsDe(id);
	}
	
	@Put("json")
	public void connecte() {
		ConnexionUtilisateurMessage commande = new ConnexionUtilisateurMessage(UUID.fromString(utilisateur.getId()));
		busCommande.envoie(commande);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private DetailsUtilisateur utilisateur;
	
}
