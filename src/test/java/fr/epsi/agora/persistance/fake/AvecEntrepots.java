package fr.epsi.agora.persistance.fake;

import org.junit.rules.ExternalResource;

import fr.epsi.agora.domaine.Entrepots;

public class AvecEntrepots extends ExternalResource {

	@Override
	protected void before() throws Throwable {
		Entrepots.setInstance(new FakeEntrepots());
	}
	
	@Override
	protected void after() {
		Entrepots.setInstance(null);
	}
	
}
