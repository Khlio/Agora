package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.DeconnexionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;

public class DeconnexionUtilisateurRessource extends ServerResource {

	@Inject
	public DeconnexionUtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		utilisateur = recherche.detailsDe(id);
	}
	
	@Put
	public void deconnecte() {
		DeconnexionUtilisateurMessage commande = new DeconnexionUtilisateurMessage(UUID.fromString(utilisateur.getId()));
		busCommande.envoie(commande);
		
		getCookieSettings().getFirst("utilisateur").setMaxAge(0);
		Session.supprime(utilisateur.getId());
		setStatus(Status.SUCCESS_ACCEPTED);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private DetailsUtilisateur utilisateur;
	
}
