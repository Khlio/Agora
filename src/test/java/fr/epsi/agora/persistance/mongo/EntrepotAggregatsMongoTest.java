package fr.epsi.agora.persistance.mongo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mongolink.MongoSession;
import org.mongolink.test.MongolinkRule;

import fr.epsi.agora.commande.FournisseurMongoSession;
import fr.epsi.agora.domaine.Aggregat;

public abstract class EntrepotAggregatsMongoTest<T extends Aggregat> {

	@Rule
	public MongolinkRule mongolinkRule = MongolinkRule.withPackage("fr.epsi.agora.persistance.mongo.mapping");
	
	@Before
	public abstract void setUp();
	
	@Test
	public abstract void peutAjouter();
	
	@Test
	public abstract void peutModifier();
	
	@Test
	public abstract void peutSupprimer();
	
	protected EntrepotAggregatsMongo<T> entrepot;
	protected FournisseurMongoSession fournisseur = new FournisseurMongoSession() {
		@Override
		public MongoSession get() {
			return mongolinkRule.getCurrentSession();
		}
		
		@Override
		public void nettoie() {
		}
	};
	
}
