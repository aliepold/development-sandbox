package aries.jpa.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import aries.jpa.mapping.JpaHibernateService;
import aries.jpa.mapping.JpaHibernateServiceImpl;

public class JpaActivator implements BundleActivator {

	private ServiceRegistration<?> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {

		Dictionary<String, Object> props = new Hashtable<String, Object>();

		props.put("osgi.command.scope", "jpa");
		props.put("osgi.command.function", new String[] { "listCustomer", "listDeveloper" });

		this.serviceRegistration = context.registerService(JpaHibernateService.class.getName(), new JpaHibernateServiceImpl(), props);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.serviceRegistration.unregister();
	}

}
