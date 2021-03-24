package hotel.services.abstracts;

import java.util.List;


public interface IService<T> {

    public void save(T model);

    public void update(T model);

    public void delete(T model);

    public T getById(Integer id);

    public List<T> getAll();

    public void close();
}
