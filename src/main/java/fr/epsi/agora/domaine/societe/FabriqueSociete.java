package fr.epsi.agora.domaine.societe;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.UUID;

public class FabriqueSociete {

	private FabriqueSociete() {
	}
	
	public static Societe nouveau(String siret, String nom) {
		checkNotNull(siret);
		checkNotNull(nom);
		
		Societe societe = new Societe(UUID.randomUUID());
		societe.setSiret(siret);
		societe.setNom(nom);
		return societe;
	}
	
}
