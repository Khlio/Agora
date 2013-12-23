package fr.epsi.agora.commande;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.inject.Inject;

public class BusCommande {

	@Inject
	public BusCommande(FournisseurMongoSession fournisseurSession) {
		this.fournisseurSession = fournisseurSession;
	}
	
	@SuppressWarnings("unchecked")
	public <V> ListenableFuture<V> envoie(Message message) {
		try {
			return Futures.immediateFuture((V) trouveHandler(message.getClass()).execute(message));
		} finally {
			fournisseurSession.nettoie();
		}
	}
	
	@SuppressWarnings("rawtypes")
	private HandlerCommande trouveHandler(Class<? extends Message> typeCommande) {
		return handlers.get(typeCommande);
	}
	
	public void enregistreHandler(final HandlerCommande<? extends Message> handler) {
		handlers.put(handler.typeCommande(), handler);
	}
	
	private final Map<Class<? extends Message>, HandlerCommande<? extends Message>> handlers = Maps.newConcurrentMap();
	private FournisseurMongoSession fournisseurSession;
	
}
