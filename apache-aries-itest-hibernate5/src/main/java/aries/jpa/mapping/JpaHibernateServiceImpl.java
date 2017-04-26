package aries.jpa.mapping;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.jpa.EntityManagerFactoryBuilder;

import aries.jpa.mapping.entities.Customer;
import aries.jpa.mapping.entities.Developer;
import aries.jpa.mapping.entities.Tester;

/**
 * Service for testing the mapping-files in a persistence.xml.
 *
 */
public class JpaHibernateServiceImpl implements JpaHibernateService {

	public static final String JPA_UNIT = "jpa.hibernate.test";

	private EntityManagerFactory emf;

	public JpaHibernateServiceImpl() {

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> listAllCustomer() throws Exception {

		List<Customer> result = null;

		try {

			EntityManager em = null;

			try {

				em = this.getEntityManager();

				System.out.println("EntityMager is created: " + em.toString());

				Query query = em.createNamedQuery(Customer.GET_CUSTOMER);

				result = query.getResultList();

				System.out.println("Count: " + result.size());
			} finally {
				if (em != null) {
					em.close();
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Developer> listAllDeveloper() throws Exception {

		List<Developer> result = null;

		try {

			EntityManager em = null;

			try {

				em = this.getEntityManager();

				System.out.println("EntityMager is created: " + em.toString());

				Query query = em.createNamedQuery(Developer.GET_DEVELOPER);

				result = query.getResultList();

				System.out.println("Count: " + result.size());

			} finally {
				if (em != null) {
					em.close();
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	@SuppressWarnings("boxing")
	public void listCustomer(String... args) {

		try {

			List<Customer> result = this.listAllCustomer();

			if (result != null) {
				for (Customer c : result) {
					System.out.println(String.format("%d %s", c.getId(), c.getName()));
				}
			} else {
				System.out.println("Result is null");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}

		System.out.println("Methode listCustomer is executed.");
	}

	@SuppressWarnings("boxing")
	public void listDeveloper(String... args) {

		try {

			List<Developer> result = this.listAllDeveloper();

			if (result != null) {
				for (Developer c : result) {
					System.out.println(String.format("%d %s", c.getId(), c.getName()));
				}
			} else {
				System.out.println("Result is null");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}

		System.out.println("Method listDeveloper  is executed.");
	}

	@SuppressWarnings("boxing")
	public void listTester(String... args) {

		try {

			List<Tester> result = this.listAllTester();

			if (result != null) {
				for (Tester c : result) {
					System.out.println(String.format("%d %s", c.getId(), c.getName()));
				}
			} else {
				System.out.println("Result is null");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}

		System.out.println("Method listTester is executed.");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tester> listAllTester() throws Exception {

		List<Tester> result = null;

		try {

			EntityManager em = null;

			try {

				em = this.getEntityManager();

				System.out.println("EntityMager is created: " + em.toString());

				Query query = em.createNamedQuery(Tester.GET_TESTER);

				result = query.getResultList();

				System.out.println("Count: " + result.size());

			} finally {
				if (em != null) {
					em.close();
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return result;
	}

	private EntityManager getEntityManager() throws Exception {
		return this.getEntityManagerFactory().createEntityManager();
	}

	private EntityManagerFactory getEntityManagerFactory() throws Exception {

		if (this.emf == null) {

			this.emf = this.getEntityManagerFactoryServiceReference();

		}
		return this.emf;
	}

	private EntityManagerFactory getEntityManagerFactoryServiceReference() throws Exception {

		Bundle thisBundle = FrameworkUtil.getBundle(JpaHibernateServiceImpl.class);

		BundleContext bc = thisBundle.getBundleContext();

		ServiceReference<?>[] serviceReferences = bc.getServiceReferences(EntityManagerFactory.class.getName(),
				String.format("(%s=%s)", EntityManagerFactoryBuilder.JPA_UNIT_NAME, JPA_UNIT));

		EntityManagerFactory emf = null;

		if (serviceReferences != null && serviceReferences.length > 0) {

			emf = (EntityManagerFactory) bc.getService(serviceReferences[0]);

		} else {
			throw new Exception("Persistenceprovider for EntityManagerFactory not found!");
		}
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

}
