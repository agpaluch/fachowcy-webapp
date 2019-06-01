package dao;

import org.hibernate.criterion.DetachedCriteria;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface RootInterfaceDAO<T> extends Serializable {

    public List<T> getAll();

    public void save(T domain);

    public T get(Serializable id);

    public void update(T domain);

    public void delete(T domain);

}
