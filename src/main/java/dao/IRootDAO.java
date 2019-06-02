package dao;

import javax.ejb.Local;
import java.io.Serializable;
import java.util.List;

@Local
public interface IRootDAO<T> extends Serializable {

    public List<T> getAll();
    public void save(T domain);
    public T get(Long id);
    public void update(T domain);
    public void delete(T domain);

}
