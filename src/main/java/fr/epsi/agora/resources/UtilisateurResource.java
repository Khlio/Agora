package fr.epsi.agora.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.epsi.agora.modeles.Utilisateur;

@Path("/utilisateurs")
public class UtilisateurResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Utilisateur> liste() {
		List<Utilisateur> liste = new ArrayList<>();
		Utilisateur utilisateur = new Utilisateur("Levacher", "Vincent", "lev.vincent@gmail.com", "pass");
		liste.add(utilisateur);
		return liste;
	}
	
}
