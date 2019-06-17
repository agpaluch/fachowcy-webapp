package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class TransactionsUtil {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    EntityManager startTransaction() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    void commit(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
