package fr.epsi.agora.web.ressource.utilisateur;

import org.restlet.data.Form;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.inject.Inject;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.utilisateur.CreationUtilisateurMessage;
import fr.epsi.agora.requete.utilisateur.RechercheUtilisateurs;
import fr.epsi.agora.web.representation.ModeleEtVue;

public class UtilisateursRessource extends ServerResource {

	@Inject
	public UtilisateursRessource(BusCommande busCommande, RechercheUtilisateurs recherche) {
		this.busCommande = busCommande;
		this.recherche = recherche;
	}
	
	@Get("json")
	public ModeleEtVue represente() {
		return ModeleEtVue.cree().avec("utilisateurs", recherche.tous());
	}
	
	@Post("json")
	public void cree(Form formulaire) {
		CreationUtilisateurMessage commande = new CreationUtilisateurMessage(
				formulaire.getFirstValue("nom"), formulaire.getFirstValue("prenom"), formulaire.getFirstValue("email"), formulaire.getFirstValue("motDePasse"),
				formulaire.getFirstValue("adresse"), formulaire.getFirstValue("telephone"));
		busCommande.envoie(commande);
	}
	
	private BusCommande busCommande;
	private RechercheUtilisateurs recherche;
	
}
