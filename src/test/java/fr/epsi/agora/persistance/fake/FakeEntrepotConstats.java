package fr.epsi.agora.persistance.fake;

import com.google.common.base.Optional;

import fr.epsi.agora.domaine.constat.Constat;
import fr.epsi.agora.domaine.constat.EntrepotConstats;

public class FakeEntrepotConstats extends FakeEntrepotAggregats<Constat> implements EntrepotConstats {

	@Override
	public Optional<Constat> get(Object id) {
		for (Constat constat : aggregats) {
			if (constat.getId().equals(id)) {
				return Optional.of(constat);
			}
		}
		return Optional.absent();
	}

}
