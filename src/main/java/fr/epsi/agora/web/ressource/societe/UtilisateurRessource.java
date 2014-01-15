package fr.epsi.agora.web.ressource.societe;

import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Method;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.societe.ModificationUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionUtilisateurMessage;
import fr.epsi.agora.domaine.MD5;
import fr.epsi.agora.domaine.validateur.EmailValidateur;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.domaine.validateur.MotDePasseValidateur;
import fr.epsi.agora.domaine.validateur.TelephoneValidateur;
import fr.epsi.agora.requete.societe.DetailsUtilisateur;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.web.Session;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class UtilisateurRessource extends ServerResource {

	@Inject
	public UtilisateurRessource(BusCommande busCommande, RechercheUtilisateurs recherche, RechercheSocietes rechercheSocietes) {
		this.busCommande = busCommande;
		this.recherche = recherche;
		this.rechercheSocietes = rechercheSocietes;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("idUtilisateur").toString());
		if (Session.get(id.toString()).isPresent() || getRequest().getMethod().equals(Method.DELETE)) {
			utilisateur = recherche.detailsDe(id);
		} else {
			setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
			setCommitted(true);
		}
	}
	
	@Get("json")
	public Representation represente() {
		try {
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.json(utilisateur);
		}  catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Put
	public Representation modifie(Form formulaire) {
		if (isCommitted()) {
			return ReponseRessource.NON_CONNECTE;
		}
		Erreur erreurEmail = EmailValidateur.valide(formulaire.getFirstValue("email"));
		if (erreurEmail.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurEmail.premiereErreur());
		}
		Erreur erreurMotDePasse = MotDePasseValidateur.valide(formulaire.getFirstValue("motDePasse"));
		if (erreurMotDePasse.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurMotDePasse.premiereErreur());
		}
		Erreur erreurTelephone = TelephoneValidateur.valide(formulaire.getFirstValue("telephone"));
		if (erreurTelephone.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurTelephone.premiereErreur());
		}
		try {
			String motDePasseCrypte = MD5.crypteAvecCle(formulaire.getFirstValue("motDePasse"));
			ModificationUtilisateurMessage commande = new ModificationUtilisateurMessage(UUID.fromString(utilisateur.getId()), formulaire.getFirstValue("nom"),
					formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), motDePasseCrypte, formulaire.getFirstValue("adresse"),
					formulaire.getFirstValue("codePostal"), formulaire.getFirstValue("telephone"));
			busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Delete
	public Representation supprime() {
		try {
			String idSociete = rechercheSocietes.societeDeLUtilisateur(UUID.fromString(utilisateur.getId())).getId();
			SuppressionUtilisateurMessage commande = new SuppressionUtilisateurMessage(UUID.fromString(utilisateur.getId()), UUID.fromString(idSociete));
			busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	private RechercheSocietes rechercheSocietes;
	private DetailsUtilisateur utilisateur;
	
}
