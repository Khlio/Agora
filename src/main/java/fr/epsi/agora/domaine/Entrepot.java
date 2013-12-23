package fr.epsi.agora.domaine;

import com.google.common.base.Optional;

public interface Entrepot<T extends Aggregat> {

	Optional<T> get(Object id);
	
	T ajoute(T aggregat);
	
	void supprime(T aggregat);
	
}
