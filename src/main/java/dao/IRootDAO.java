package dao;

import javax.ejb.Local;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Local
public interface IRootDAO<T> extends Serializable {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("primary");

    List<T> getAll();
    void save(T domain);
    Optional<T> get(Long id);
    void delete(Long id);

}
