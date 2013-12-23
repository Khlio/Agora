package fr.epsi.agora.commande.utilisateur;

import fr.epsi.agora.commande.Message;

public class CreationUtilisateurMessage implements Message {

	public CreationUtilisateurMessage(String nom, String prenom, String email, String motDePasse) {
		this(nom, prenom, email, motDePasse, "", "");
	}
	
	public CreationUtilisateurMessage(String nom, String prenom, String email, String motDePasse, String adresse, String telephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	
	public final String nom;
	public final String prenom;
	public final String email;
	public final String motDePasse;
	public final String adresse;
	public final String telephone;
	
}
