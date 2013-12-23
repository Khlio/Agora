package fr.epsi.agora.web.restlet;

import org.restlet.Context;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.resource.Finder;
import org.restlet.resource.ServerResource;

import com.google.inject.Injector;

public class GuiceFinder extends Finder {

	public GuiceFinder(final Context context, final Class<? extends ServerResource> target, final Injector injector) {
		super(context, target);
		this.injector = injector;
	}
	
	@Override
	public ServerResource create(Request request, Response response) {
		return (ServerResource) injector.getInstance(getTargetClass());
	}
	
	private final Injector injector;
	
}
