package fr.epsi.agora.commande.societe;

import java.util.UUID;

import fr.epsi.agora.commande.Message;

public class AjoutClientMessage implements Message {

	public AjoutClientMessage(UUID idSociete, String nom, String prenom, String email, String dateDeNaissance, String lieuDeNaissance, String metier,
			String nationalite, String adresse1, String adresse2, String codePostal, String telephonePortable, String telephoneFixe) {
		this.idSociete = idSociete;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.metier = metier;
		this.nationalite = nationalite;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.telephonePortable = telephonePortable;
		this.telephoneFixe = telephoneFixe;
	}
	
	public final UUID idSociete;
	public final String nom;
	public final String prenom;
	public final String email;
	public final String dateDeNaissance;
	public final String lieuDeNaissance;
	public final String metier;
	public final String nationalite;
	public final String adresse1;
	public final String adresse2;
	public final String codePostal;
	public final String telephonePortable;
	public final String telephoneFixe;
	
}
