package de.aliepold.aries.jpa.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.aliepold.aries.jpa.mapping.MappingService;
import de.aliepold.aries.jpa.mapping.MappingServiceImpl;

public class JpaActivator implements BundleActivator {

	private ServiceRegistration<?> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {

		Dictionary<String, Object> props = new Hashtable<String, Object>();

		props.put("osgi.command.scope", "jpa");
		props.put("osgi.command.function", new String[] { "listCustomer", "listDeveloper", "listTester" });

		this.serviceRegistration = context.registerService(MappingService.class.getName(), new MappingServiceImpl(), props);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.serviceRegistration.unregister();
	}

}
