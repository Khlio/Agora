package fr.epsi.agora.web.ressource;

import org.restlet.ext.jackson.JacksonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;

import fr.epsi.agora.domaine.validateur.Erreur;

public class ReponseRessource {

	public static final Representation OK = new StringRepresentation("");
	public static final Representation ERREUR = new StringRepresentation(Erreur.ERREUR_INCONNUE);
	public static final Representation NON_CONNECTE = new StringRepresentation(Erreur.NON_CONNECTE);

	private ReponseRessource() {
	}

	public static Representation get(String texte) {
		return new StringRepresentation(texte);
	}

	public static Representation json(Object objet) {
		return new JacksonRepresentation<>(objet);
	}
	
}
