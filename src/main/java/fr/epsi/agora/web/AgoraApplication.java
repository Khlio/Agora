package fr.epsi.agora.web;

import java.util.Set;

import org.reflections.Reflections;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import fr.epsi.agora.commande.BusCommande;
import fr.epsi.agora.commande.HandlerCommande;
import fr.epsi.agora.domaine.Entrepots;
import fr.epsi.agora.web.configuration.AgoraRouter;
import fr.epsi.agora.web.configuration.GuiceProductionModule;

public class AgoraApplication extends Application {

	public AgoraApplication(Context context) {
		super(context);
		injector = Guice.createInjector(Stage.PRODUCTION, new GuiceProductionModule());
		Entrepots.setInstance(injector.getInstance(Entrepots.class));
		configureBusCommande();
	}
	
	@Override
	public synchronized Restlet createInboundRoot() {
		return new AgoraRouter(getContext(), injector);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void configureBusCommande() {
		BusCommande busCommande = injector.getInstance(BusCommande.class);
		Set<Class<? extends HandlerCommande>> handlers = new Reflections("fr.epsi.agora.commande").getSubTypesOf(HandlerCommande.class);
		for (Class<? extends HandlerCommande> handler : handlers) {
			busCommande.enregistreHandler(injector.getInstance(handler));
		}
	}
	
	private Injector injector;
	
}
