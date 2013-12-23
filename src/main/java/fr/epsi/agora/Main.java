package fr.epsi.agora;

import java.io.IOException;

import org.restlet.Component;
import org.restlet.data.Protocol;

import fr.epsi.agora.web.configuration.FabriqueApplication;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8080);
		component.getDefaultHost().attach(new FabriqueApplication().configure());
		try {
			component.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
