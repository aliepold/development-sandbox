package de.aliepold.aries.jpa.mapping.itests;

import static org.ops4j.pax.exam.CoreOptions.composite;
import static org.ops4j.pax.exam.CoreOptions.frameworkProperty;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;
import static org.ops4j.pax.exam.CoreOptions.vmOption;
import static org.ops4j.pax.exam.CoreOptions.when;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.options.MavenArtifactProvisionOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.util.tracker.ServiceTracker;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public abstract class AbstractJPAIntegrationTest {

	@Inject
	protected BundleContext bundleContext;

	@Inject
	protected UserTransaction ut;

	@Inject
	protected ConfigurationAdmin configAdmin;


	protected Option ariesJpa21() {
		return composite(
				this.ariesJpaInternal(),
				this.mvnBundle("org.hibernate.javax.persistence", "hibernate-jpa-2.1-api")
				);
	}

	private Option ariesJpaInternal() {
		return composite(

				frameworkProperty("org.osgi.framework.system.packages.extra")
				.value("javax.accessibility,javax.activation,javax.activity,javax.annotation,javax.annotation.processing,javax.crypto,javax.crypto.interfaces,javax.crypto.spec,javax.imageio,javax.imageio.event,javax.imageio.metadata,javax.imageio.plugins.bmp,javax.imageio.plugins.jpeg,javax.imageio.spi,javax.imageio.stream,javax.jws,javax.jws.soap,javax.lang.model,javax.lang.model.element,javax.lang.model.type,javax.lang.model.util,javax.management,javax.management.loading,javax.management.modelmbean,javax.management.monitor,javax.management.openmbean,javax.management.relation,javax.management.remote,javax.management.remote.rmi,javax.management.timer,javax.naming,javax.naming.directory,javax.naming.event,javax.naming.ldap,javax.naming.spi,javax.net,javax.net.ssl,javax.print,javax.print.attribute,javax.print.attribute.standard,javax.print.event,javax.rmi,javax.rmi.CORBA,javax.rmi.ssl,javax.script,javax.security.auth,javax.security.auth.callback,javax.security.auth.kerberos,javax.security.auth.login,javax.security.auth.spi,javax.security.auth.x500,javax.security.cert,javax.security.sasl,javax.sound.midi,javax.sound.midi.spi,javax.sound.sampled,javax.sound.sampled.spi,javax.sql,javax.sql.rowset,javax.sql.rowset.serial,javax.sql.rowset.spi,javax.swing,javax.swing.border,javax.swing.colorchooser,javax.swing.event,javax.swing.filechooser,javax.swing.plaf,javax.swing.plaf.basic,javax.swing.plaf.metal,javax.swing.plaf.multi,javax.swing.plaf.synth,javax.swing.table,javax.swing.text,javax.swing.text.html,javax.swing.text.html.parser,javax.swing.text.rtf,javax.swing.tree,javax.swing.undo,javax.tools,javax.xml,javax.xml.bind,javax.xml.bind.annotation,javax.xml.bind.annotation.adapters,javax.xml.bind.attachment,javax.xml.bind.helpers,javax.xml.bind.util,javax.xml.crypto,javax.xml.crypto.dom,javax.xml.crypto.dsig,javax.xml.crypto.dsig.dom,javax.xml.crypto.dsig.keyinfo,javax.xml.crypto.dsig.spec,javax.xml.datatype,javax.xml.namespace,javax.xml.parsers,javax.xml.soap,"
						+ "javax.xml.stream; version=1.0,javax.xml.stream.events; version=1.0,javax.xml.stream.util; version=1.0,"
						+ "javax.servlet,javax.servlet.http,javax.xml.transform,javax.xml.transform.dom,javax.xml.transform.sax,javax.xml.transform.stax,javax.xml.transform.stream,javax.xml.validation,javax.xml.ws,javax.xml.ws.handler,javax.xml.ws.handler.soap,javax.xml.ws.http,javax.xml.ws.soap,javax.xml.ws.spi,javax.xml.xpath,org.ietf.jgss,org.omg.CORBA,org.omg.CORBA.DynAnyPackage,org.omg.CORBA.ORBPackage,org.omg.CORBA.TypeCodePackage,org.omg.CORBA.portable,org.omg.CORBA_2_3,org.omg.CORBA_2_3.portable,org.omg.CosNaming,org.omg.CosNaming.NamingContextExtPackage,org.omg.CosNaming.NamingContextPackage,org.omg.Dynamic,org.omg.DynamicAny,org.omg.DynamicAny.DynAnyFactoryPackage,org.omg.DynamicAny.DynAnyPackage,org.omg.IOP,org.omg.IOP.CodecFactoryPackage,org.omg.IOP.CodecPackage,org.omg.Messaging,org.omg.PortableInterceptor,org.omg.PortableInterceptor.ORBInitInfoPackage,org.omg.PortableServer,org.omg.PortableServer.CurrentPackage,org.omg.PortableServer.POAManagerPackage,org.omg.PortableServer.POAPackage,org.omg.PortableServer.ServantLocatorPackage,org.omg.PortableServer.portable,org.omg.SendingContext,org.omg.stub.java.rmi,org.w3c.dom,org.w3c.dom.bootstrap,org.w3c.dom.css,org.w3c.dom.events,org.w3c.dom.html,org.w3c.dom.ls,org.w3c.dom.ranges,org.w3c.dom.stylesheets,org.w3c.dom.traversal,org.w3c.dom.views,org.xml.sax,org.xml.sax.ext,org.xml.sax.helpers"),


				CoreOptions.systemProperty("derby.stream.error.file").value("target/derby.log"),

				this.mvnBundle("org.ow2.asm", "asm-all"),
				this.mvnBundle("org.apache.felix", "org.apache.felix.configadmin"),
				this.mvnBundle("org.apache.felix", "org.apache.felix.coordinator"),

				this.mvnBundle("org.apache.aries.proxy", "org.apache.aries.proxy.api"),
				this.mvnBundle("org.apache.aries.proxy", "org.apache.aries.proxy.impl"),
				this.mvnBundle("org.apache.aries", "org.apache.aries.util"),

				this.mvnBundle("org.apache.aries.jndi", "org.apache.aries.jndi.api"),
				this.mvnBundle("org.apache.aries.jndi", "org.apache.aries.jndi.core"),
				this.mvnBundle("org.apache.aries.jndi", "org.apache.aries.jndi.url"),

				this.mvnBundle("org.apache.aries.blueprint", "org.apache.aries.blueprint.api"),
				this.mvnBundle("org.apache.aries.blueprint", "org.apache.aries.blueprint.core"),

				this.mvnBundle("org.apache.aries.jpa", "org.apache.aries.jpa.api"),
				this.mvnBundle("org.apache.aries.jpa", "org.apache.aries.jpa.container"),
				this.mvnBundle("org.apache.aries.jpa", "org.apache.aries.jpa.support"),
				this.mvnBundle("org.apache.aries.jpa", "org.apache.aries.jpa.blueprint"),

				this.mvnBundle("org.apache.aries.transaction", "org.apache.aries.transaction.manager"),
				this.mvnBundle("org.apache.aries.transaction", "org.apache.aries.transaction.blueprint")

				);
	}

	protected Option baseOptions() {
		String localRepo = System.getProperty("maven.repo.local");

		if (localRepo == null) {
			localRepo = System.getProperty("org.ops4j.pax.url.mvn.localRepository");
		}
		return composite(junitBundles(),
				frameworkProperty("org.osgi.framework.bootdelegation")
				.value("*"),
				mavenBundle("org.ops4j.pax.logging", "pax-logging-api", "1.7.2"),
				mavenBundle("org.ops4j.pax.logging", "pax-logging-service", "1.7.2"),
				mavenBundle("org.ops4j.base","ops4j-base-lang").version("1.5.0"),
				mavenBundle("org.ops4j.base","ops4j-base-store").version("1.5.0"),
				mavenBundle("org.ops4j.base","ops4j-base-util-property").version("1.5.0"),
				mavenBundle("org.ops4j.base","ops4j-base-io").version("1.5.0"),

				systemProperty("org.ops4j.pax.logging.DefaultServiceLog.level").value("INFO"),

				when(localRepo != null).useOptions(vmOption("-Dorg.ops4j.pax.url.mvn.localRepository=" + localRepo))
				);
	}

	@Before
	public void createConfigForLogging() throws IOException {
		Configuration logConfig = this.configAdmin.getConfiguration("org.ops4j.pax.logging", null);
		Dictionary<String, String> props = new Hashtable<String, String>();
		props.put("log4j.rootLogger", "INFO, stdout");
		props.put("log4j.logger.org.apache.aries.transaction", "DEBUG");
		props.put("log4j.logger.org.apache.aries.transaction.parsing", "DEBUG");
		props.put("log4j.logger.org.apache.aries.jpa.blueprint.impl", "DEBUG");
		props.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		props.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
		props.put("log4j.appender.stdout.layout.ConversionPattern", "%d{ISO8601} | %-5.5p | %-16.16t | %c | %m%n");
		logConfig.update(props);
	}

	protected Option debug() {
		return vmOption("-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005");
	}

	protected Option hsqldbDSF() {
		return composite(
				this.mvnBundle("org.hsqldb", "hsqldb")
				);
	}

	protected Option hibernate() {
		return composite(
				this.mvnBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.antlr"),
				this.mvnBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.ant"),
				this.mvnBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.dom4j"),
				this.mvnBundle("org.apache.servicemix.bundles" , "org.apache.servicemix.bundles.serp"),
				this.mvnBundle("com.fasterxml", "classmate"),
				this.mvnBundle("org.javassist", "javassist"),
				this.mvnBundle("org.jboss.logging", "jboss-logging"),
				this.mvnBundle("org.jboss", "jandex"),
				this.mvnBundle("org.hibernate.common", "hibernate-commons-annotations"),
				this.mvnBundle("org.hibernate", "hibernate-core"),
				this.mvnBundle("org.hibernate", "hibernate-entitymanager"),
				this.mvnBundle("org.hibernate", "hibernate-osgi")
				);
	}

	protected Option jta12Bundles() {
		return composite(
				this.mvnBundle("javax.interceptor", "javax.interceptor-api"),
				this.mvnBundle("org.apache.servicemix.bundles", "org.apache.servicemix.bundles.javax-inject"),
				this.mvnBundle("javax.el", "javax.el-api"),
				this.mvnBundle("javax.enterprise", "cdi-api"),
				this.mvnBundle("javax.transaction", "javax.transaction-api")
				);
	}

	private MavenArtifactProvisionOption mvnBundle(String groupId, String artifactId) {
		return mavenBundle(groupId, artifactId).versionAsInProject();
	}

	protected EntityManagerFactory getEMF(String name) {
		return this.getService(EntityManagerFactory.class, "osgi.unit.name=" + name);
	}

	public <T> T getService(Class<T> type, String filter) {
		return this.getService(type, filter, 10000);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> T getService(Class<T> type, String filter, int timeout) {
		ServiceTracker tracker = null;
		try {
			String objClassFilter = "(" + Constants.OBJECTCLASS + "=" + type.getName() + ")";
			String flt = filter != null ? "(&" + objClassFilter + this.sanitizeFilter(filter) + ")" : objClassFilter;
			Filter osgiFilter = FrameworkUtil.createFilter(flt);
			tracker = new ServiceTracker(this.bundleContext, osgiFilter, null);
			tracker.open();

			Object svc = type.cast(tracker.waitForService(timeout));
			if (svc == null) {
				throw new IllegalStateException("Gave up waiting for service " + flt);
			}
			return type.cast(svc);
		} catch (InvalidSyntaxException e) {
			throw new IllegalArgumentException("Invalid filter", e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			tracker.close();
		}
	}

	public String sanitizeFilter(String filter) {
		return filter.startsWith("(") ? filter : "(" + filter + ")";
	}

}
