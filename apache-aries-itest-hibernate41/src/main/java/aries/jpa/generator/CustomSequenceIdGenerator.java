package aries.jpa.generator;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

public class CustomSequenceIdGenerator implements IdentifierGenerator, Configurable {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		throw new HibernateException("Uppss, no generator...");
	}


	@Override
	public void configure(Type type, Properties params, Dialect d) throws MappingException {
		// TODO Auto-generated method stub

	}

}
