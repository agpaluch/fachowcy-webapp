package config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;

@Singleton
@DataSourceDefinition(name = "java:app/fachmann",
        className = "org.h2.jdbcx.JdbcDataSource",
        user = "sa",
        password = "",
        url = "jdbc:h2:mem:test;DB_CLOSE_ON_EXIT=FALSE;INIT=runscript from '/home/agnieszka/Desktop/fachowcy-webapp/src/main/resources/db.migration/V1__create_database.sql'")
       /* url = "jdbc:h2:mem:fachmann;DB_CLOSE_DELAY=-1")*/
public class DatasourceProvider {
}
