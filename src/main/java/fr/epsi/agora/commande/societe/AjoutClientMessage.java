package fr.epsi.agora.commande.societe;

import java.util.Date;
import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class AjoutClientMessage implements Message {

	public AjoutClientMessage(UUID idSociete, String nom, String prenom, String email, Date dateDeNaissance, String lieuDeNaissance, String metier,
			String nationalite, String adresse, String telephone) {
		this.idSociete = idSociete;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.metier = metier;
		this.nationalite = nationalite;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	
	public final UUID idSociete;
	public final String nom;
	public final String prenom;
	public final String email;
	public final Date dateDeNaissance;
	public final String lieuDeNaissance;
	public final String metier;
	public final String nationalite;
	public final String adresse;
	public final String telephone;
	
}
