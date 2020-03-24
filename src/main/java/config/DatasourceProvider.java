package config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;

@Singleton
@DataSourceDefinition(name = "java:app/fachmann",
        className = "org.h2.jdbcx.JdbcDataSource",
        user = "sa",
        password = "",
        url = "jdbc:h2:mem:test;INIT=runscript from 'classpath:db.migration/V1__create_database.sql'")
public class DatasourceProvider {
}
