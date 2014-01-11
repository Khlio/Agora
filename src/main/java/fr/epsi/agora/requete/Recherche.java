package fr.epsi.agora.requete;

import org.jongo.Jongo;

public abstract class Recherche {

	public Recherche(Jongo jongo) {
		this.jongo = jongo;
	}
	
	protected Jongo jongo;
	
}
