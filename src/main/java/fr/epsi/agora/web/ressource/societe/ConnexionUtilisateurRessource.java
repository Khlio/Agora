package fr.epsi.agora.web.ressource.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

import org.restlet.data.CookieSetting;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ConnexionUtilisateurMessage;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;

public class ConnexionUtilisateurRessource extends ServerResource {

	@Inject
	public ConnexionUtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Put
	public void connecte(Form formulaire) {
		DetailsUtilisateur details = recherche.detailsDe(formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"));
		try {
			checkNotNull(details);
			
			ConnexionUtilisateurMessage commande = new ConnexionUtilisateurMessage(UUID.fromString(details.getId()));
			busCommande.envoie(commande);
			
			Session.ajoute(details.getId(), UUID.randomUUID());
			CookieSetting cookie = new CookieSetting(1, "utilisateur", details.getId());
			getCookieSettings().add(cookie);
			setStatus(Status.SUCCESS_ACCEPTED);
		} catch (NullPointerException npe) {
			setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
		}
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	
}
