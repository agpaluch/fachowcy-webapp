package domain;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;


@ApplicationScoped
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Domyślnie weźmie hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public EntityManager getEntityManager() {
        EntityManager em = buildSessionFactory().createEntityManager();
        return em;
    }

    public static void shutdown() {
        // Czyści cache i connection poole
        getSessionFactory().close();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Alternatywny sposób konfigurowania, ale łatwiej o pomyłkę
    /*private static Configuration getConfiguration() {
        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/isa?useSSL=false");
        cfg.setProperty("hibernate.connection.username", "root");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.hbm2ddl.auto", "validate");
        // cfg.addAnnotatedClass(Product.class);
        cfg.addResource("isa.hbm.xml");
        return cfg;
    }*/
}