package fr.epsi.agora.persistance.mongo;

import com.google.common.base.Optional;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.Aggregat;
import fr.epsi.agora.domaine.Entrepot;

public abstract class EntrepotAggregatsMongo<T extends Aggregat> implements Entrepot<T> {

	public EntrepotAggregatsMongo(FournisseurMongoSession fournisseur) {
		this.fournisseur = fournisseur;
	}
	
	@Override
	public abstract Optional<T> get(Object id);

	@Override
	public T ajoute(T aggregat) {
		fournisseur.get().save(aggregat);
		return aggregat;
	}

	@Override
	public void modifie(T aggregat) {
		fournisseur.get().update(aggregat);
	}

	@Override
	public void supprime(T aggregat) {
		fournisseur.get().delete(aggregat);
	}
	
	protected FournisseurMongoSession fournisseur;

}
