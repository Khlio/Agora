package fr.epsi.agora.commande.utilisateur;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class ModificationUtilisateurMessage implements Message {

	public ModificationUtilisateurMessage(UUID id, String nom, String prenom, String email, String motDePasse, String adresse, String telephone) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	
	public final UUID id;
	public final String nom;
	public final String prenom;
	public final String email;
	public final String motDePasse;
	public final String adresse;
	public final String telephone;
	
}
