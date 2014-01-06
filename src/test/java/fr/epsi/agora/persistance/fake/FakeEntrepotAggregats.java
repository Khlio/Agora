package fr.epsi.agora.persistance.fake;

import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import fr.epsi.agora.domaine.Aggregat;
import fr.epsi.agora.domaine.Entrepot;

public abstract class FakeEntrepotAggregats<T extends Aggregat> implements Entrepot<T> {

	@Override
	public abstract Optional<T> get(Object id);
	
	@Override
	public T ajoute(T aggregat) {
		aggregats.add(aggregat);
		return aggregat;
	}

	@Override
	public void modifie(T aggregat) {
		supprime(get(aggregat.getId()).get());
		aggregats.add(aggregat);
	}

	@Override
	public void supprime(T aggregat) {
		aggregats.remove(aggregat);
	}
	
	protected List<T> aggregats = Lists.newArrayList();
	
}
