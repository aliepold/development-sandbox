package de.aliepold.aries.jpa.activator;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.persistence.spi.PersistenceProvider;

import org.hibernate.ejb.HibernatePersistence;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.aliepold.aries.jpa.mapping.JpaHibernateService;
import de.aliepold.aries.jpa.mapping.JpaHibernateServiceImpl;

public class JpaActivator implements BundleActivator {

	private static final String JAVAX_PERSISTENCE_PROVIDER_PROP = "javax.persistence.provider";

	private ServiceRegistration<?> serviceRegistration;

	@Override
	public void start(BundleContext context) throws Exception {

		Dictionary<String, Object> props = new Hashtable<String, Object>();

		props.put("osgi.command.scope", "jpa");
		props.put("osgi.command.function", new String[] { "listCustomer", "listDeveloper" });

		this.serviceRegistration = context.registerService(JpaHibernateService.class.getName(), new JpaHibernateServiceImpl(), props);

		HibernatePersistence persistence = new HibernatePersistence();
		props = new Hashtable<String, Object>();
		props.put(JAVAX_PERSISTENCE_PROVIDER_PROP, persistence.getClass().getName());
		this.serviceRegistration = context.registerService(PersistenceProvider.class.getName(), persistence, props);

	}

	@Override
	public void stop(BundleContext context) throws Exception {
		this.serviceRegistration.unregister();
	}

}
