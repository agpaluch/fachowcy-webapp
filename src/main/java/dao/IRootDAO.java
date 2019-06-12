package dao;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

@Local
public interface IRootDAO<T> extends Serializable {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    List<T> getAll();
    T save(T domain);
    T get(Long id);
    void update(T domain);
    void delete(T domain);

    default EntityManager startTransaction() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        return em;
    }

    default void commit(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
