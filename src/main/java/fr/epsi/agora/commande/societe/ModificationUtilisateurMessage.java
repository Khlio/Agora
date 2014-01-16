package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class ModificationUtilisateurMessage implements Message {

	public ModificationUtilisateurMessage(UUID id, String nom, String prenom, String motDePasse, String adresse, String codePostal, String telephone) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.telephone = telephone;
	}
	
	public final UUID id;
	public final String nom;
	public final String prenom;
	public final String motDePasse;
	public final String adresse;
	public final String codePostal;
	public final String telephone;
	
}
