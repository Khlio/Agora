package fr.epsi.agora.web.ressource.societe;

import java.util.List;
import java.util.UUID;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.constat.SuppressionConstatMessage;
import fr.epsi.agora.commande.societe.AjoutUtilisateurMessage;
import fr.epsi.agora.commande.societe.SuppressionClientMessage;
import fr.epsi.agora.commande.societe.SuppressionSocieteMessage;
import fr.epsi.agora.commande.societe.SuppressionUtilisateurMessage;
import fr.epsi.agora.domaine.validateur.EmailValidateur;
import fr.epsi.agora.domaine.validateur.Erreur;
import fr.epsi.agora.domaine.validateur.TelephoneValidateur;
import fr.epsi.agora.requete.constat.DetailsConstat;
import fr.epsi.agora.requete.constat.RechercheConstats;
import fr.epsi.agora.requete.societe.DetailsSociete;
import fr.epsi.agora.requete.societe.RechercheClients;
import fr.epsi.agora.requete.societe.RechercheSocietes;
import fr.epsi.agora.requete.societe.RechercheUtilisateurs;
import fr.epsi.agora.requete.societe.ResumeClient;
import fr.epsi.agora.requete.societe.ResumeUtilisateur;
import fr.epsi.agora.web.ressource.ReponseRessource;

public class SocieteRessource extends ServerResource {

	@Inject
	public SocieteRessource(BusCommande busCommande, RechercheSocietes recherche, RechercheUtilisateurs rechercheUtilisateurs, RechercheClients rechercheClients,
			RechercheConstats rechercheConstats) {
		this.busCommande = busCommande;
		this.recherche = recherche;
		this.rechercheUtilisateurs = rechercheUtilisateurs;
		this.rechercheClients = rechercheClients;
		this.rechercheConstats = rechercheConstats;
	}
	
	@Override
	protected void doInit() {
		UUID id = UUID.fromString(getRequestAttributes().get("id").toString());
		societe = recherche.detailsDe(id);
	}
	
	@Get("json")
	public Representation represente() {
		try {
			return ReponseRessource.json(societe);
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Put
	public Representation ajouteUtilisateur(Form formulaire) {
		Erreur erreurEmail = EmailValidateur.valide(formulaire.getFirstValue("email"));
		if (erreurEmail.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurEmail.premiereErreur());
		}
		Erreur erreurTelephone = TelephoneValidateur.valide(formulaire.getFirstValue("telephone"));
		if (erreurTelephone.aDesErreurs()) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.get(erreurTelephone.premiereErreur());
		}
		try {
			AjoutUtilisateurMessage commande = new AjoutUtilisateurMessage(UUID.fromString(societe.getId()), formulaire.getFirstValue("nom"),
					formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"),
					formulaire.getFirstValue("adresse"), formulaire.getFirstValue("codePostal"), formulaire.getFirstValue("telephone"));
			ListenableFuture<UUID> idUtilisateur = busCommande.envoie(commande);
			setStatus(Status.SUCCESS_ACCEPTED);
			return ReponseRessource.get(Futures.getUnchecked(idUtilisateur).toString());
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	@Delete
	public Representation supprime() {
		try {
			List<ResumeUtilisateur> utilisateurs = rechercheUtilisateurs.tousDuneSociete(societe);
			for (ResumeUtilisateur utilisateur : utilisateurs) {
				SuppressionUtilisateurMessage commandeUtilisateur = new SuppressionUtilisateurMessage(UUID.fromString(utilisateur.getId()), UUID.fromString(societe.getId()));
				busCommande.envoie(commandeUtilisateur);
			}
			
			List<ResumeClient> clients = rechercheClients.tousDuneSociete(societe);
			for (ResumeClient client : clients) {
				List<DetailsConstat> constats = rechercheConstats.tousDunClient(UUID.fromString(client.getId()));
				for (DetailsConstat constat : constats) {
					SuppressionConstatMessage commandeConstat = new SuppressionConstatMessage(UUID.fromString(constat.getId()));
					busCommande.envoie(commandeConstat);
				}
				SuppressionClientMessage commandeClient = new SuppressionClientMessage(UUID.fromString(client.getId()), UUID.fromString(societe.getId()));
				busCommande.envoie(commandeClient);
			}
			
	        SuppressionSocieteMessage commande = new SuppressionSocieteMessage(UUID.fromString(societe.getId()));
	        busCommande.envoie(commande);
	        setStatus(Status.SUCCESS_ACCEPTED);
	        return ReponseRessource.OK;
		} catch (Exception e) {
			setStatus(Status.CLIENT_ERROR_BAD_REQUEST);
			return ReponseRessource.ERREUR;
		}
	}
	
	private BusCommande busCommande;
	private RechercheSocietes recherche;
	private RechercheUtilisateurs rechercheUtilisateurs;
	private RechercheClients rechercheClients;
	private RechercheConstats rechercheConstats;
	private DetailsSociete societe;
	
}
