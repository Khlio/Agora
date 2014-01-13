package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class AjoutUtilisateurMessage implements Message {

	public AjoutUtilisateurMessage(UUID idSociete, String nom, String prenom, String email, String motDePasse, String adresse, String codePostal, String telephone) {
		this.idSociete = idSociete;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.telephone = telephone;
	}
	
	public final UUID idSociete;
	public final String nom;
	public final String prenom;
	public final String email;
	public final String motDePasse;
	public final String adresse;
	public final String codePostal;
	public final String telephone;

}
