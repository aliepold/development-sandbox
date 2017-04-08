package aries.jpa.mapping.itests;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import aries.jpa.mapping.MappingServiceImpl;
import aries.jpa.mapping.entities.Customer;
import aries.jpa.mapping.entities.Developer;
import aries.jpa.mapping.entities.Tester;

/**
 * IntegrationTest for mapping files.
 */
public class MappingIntegrationTest extends AbstractJPAIntegrationTest{

	static Logger log = LoggerFactory.getLogger(MappingIntegrationTest.class);

	@Configuration
	public Option[] config() throws FileNotFoundException {

		return org.ops4j.pax.exam.CoreOptions.options(

				this.baseOptions()
				,this.jta12Bundles()
				,this.ariesJpa21()
				,this.hsqldbDSF()
				//,this.debug()
				,this.hibernate()
				,CoreOptions.url(String.format("link:file:target/links/aries.jpa.mapping.test.link")).start()
				);

	}

	@ProbeBuilder
	public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {

		probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "*,org.apache.felix.service.*;status=provisional");
		probe.setHeader(Constants.EXPORT_PACKAGE, MappingIntegrationTest.class.getPackage().getName());
		return probe;
	}

	@Before
	public void setUp() throws Exception {

		super.createConfigForLogging();
	}


	@After
	public void tearDown() throws Exception {

	}

	/**
	 * Testmethod for mapping Customer object in persistence.xml
	 *
	 * @throws Exception
	 */
	@Test
	public void testMappingFilesCustomer() throws Exception {

		EntityManagerFactory emf =  super.getEMF(MappingServiceImpl.JPA_UNIT);

		MappingServiceImpl msTest = new MappingServiceImpl();
		msTest.setEmf(emf);

		List<Customer> actual = msTest.listAllCustomer();

		assertNotNull(actual);
	}

	/**
	 * Testmethod for mapping Tester object in mapped file orm.xml
	 *
	 * @throws Exception
	 */
	@Test
	public void testMappingFilesTester() throws Exception {

		EntityManagerFactory emf =  super.getEMF(MappingServiceImpl.JPA_UNIT);

		MappingServiceImpl msTest = new MappingServiceImpl();
		msTest.setEmf(emf);

		List<Tester> actual = msTest.listAllTester();

		assertNotNull(actual);
	}

	/**
	 * Testmethod for mapping Developer object mapped developer-orm.xml
	 *
	 * @throws Exception
	 */
	@Test
	public void testMappingFilesDeveloper() throws Exception {

		EntityManagerFactory emf =  super.getEMF(MappingServiceImpl.JPA_UNIT);

		MappingServiceImpl msTest = new MappingServiceImpl();
		msTest.setEmf(emf);

		List<Developer> actual = msTest.listAllDeveloper();

		assertNotNull(actual);
	}
}
