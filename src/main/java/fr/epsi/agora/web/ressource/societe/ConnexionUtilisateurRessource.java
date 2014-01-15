package fr.epsi.agora.web.ressource.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.Constante;
import fr.epsi.agora.domaine.MD5;
import fr.epsi.agora.domaine.validateur.EmailValidateur;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class ConnexionUtilisateurRessource extends ServerResource {

	@Inject
	public ConnexionUtilisateurRessource(RechercheUtilisateurs recherche) {
		this.recherche = recherche;
	}
	
	@Post
	public Representation connecte(Form formulaire) {
		Erreur erreurEmail = EmailValidateur.valide(formulaire.getFirstValue("email"));
		if (erreurEmail.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurEmail.premiereErreur());
		}
		try {
			String motDePasseCrypte = MD5.crypte(Constante.CLE_CRYPTAGE + formulaire.getFirstValue("motDePasse"));
			DetailsUtilisateur details = recherche.detailsDe(formulaire.getFirstValue("email"), motDePasseCrypte);
			checkNotNull(details);
			
			Session.ajoute(details.getId());
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.get(details.getId());
		} catch (NullPointerException npe) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(Erreur.IDENTIFIANTS_INTROUVABLE);
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private RechercheUtilisateurs recherche;
	
}
