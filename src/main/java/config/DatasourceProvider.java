package config;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;

@Singleton
@DataSourceDefinition(name = "java:app/fachmann",
        className = "com.mysql.cj.jdbc.MysqlXADataSource",
        user = "root",
        password = "password",
        url = "jdbc:mysql://localhost:3306/fachmann?useSSL=false")
public class DatasourceProvider {
}
