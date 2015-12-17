package com.oak.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableCassandraRepositories(basePackages = { "com.oak.repositories" })
@PropertySource(value = { "classpath:cassandra.properties" })
public class CassandraConfig {
	private static final Logger LOG = LoggerFactory
			.getLogger(CassandraConfig.class);

	/**
	 * Constant String for Keyspace
	 */
	private static final String KEYSPACE = "cassandra.keyspace";
	/**
	 * Constant String for ContactPoints
	 */
	private static final String CONTACTPOINTS = "cassandra.contactpoints";
	/**
	 * Constant String for Port
	 */
	private static final String PORT = "cassandra.port";

	@Autowired
	private Environment environment;

	@Bean
	public CassandraClusterFactoryBean cluster() {

		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints(getContactPoints());
		cluster.setPort(getPortNumber());
		return cluster;
	}

	@Bean
	public CassandraMappingContext mappingContext() {
		return new BasicCassandraMappingContext();
	}

	@Bean
	public CassandraConverter converter() {
		return new MappingCassandraConverter(mappingContext());
	}

	@Bean
	public CassandraSessionFactoryBean session() throws Exception {

		CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
		session.setCluster(cluster().getObject());
		session.setKeyspaceName(getKeyspaceName());
		session.setConverter(converter());
		session.setSchemaAction(SchemaAction.NONE);
		LOG.debug("cassendra session is created successfully");
		return session;
	}

	@Bean
	public CassandraOperations cassandraTemplate() throws Exception {
		return new CassandraTemplate(session().getObject());
	}

	private String getKeyspaceName() {
		return environment.getProperty(KEYSPACE);
	}

	private String getContactPoints() {
		return environment.getProperty(CONTACTPOINTS);
	}

	private int getPortNumber() {
		return Integer.parseInt(environment.getProperty(PORT));
	}

}
