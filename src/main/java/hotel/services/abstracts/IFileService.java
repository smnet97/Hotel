package hotel.services.abstracts;


import hotel.models.Model;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Sherzod on 14.12.2016.
 */
public interface IFileService<T extends Model> extends IService<T> {

    InputStream getSource();

    Integer getNextId();

    void setNextId(Integer nextId);

    T lineToObject(String line);

    void writeToFile();

    void writeToFile(String line);

    public void loadToMap();

    List<T> findByType(String type);
}
