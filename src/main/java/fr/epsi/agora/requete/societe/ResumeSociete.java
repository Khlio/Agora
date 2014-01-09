package fr.epsi.agora.requete.societe;

import org.jongo.marshall.jackson.oid.Id;

public class ResumeSociete {

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSiret() {
		return siret;
	}
	
	public void setSiret(String siret) {
		this.siret = siret;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Id
	private String id;
	private String siret;
	private String nom;
	
}
