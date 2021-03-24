package hotel.services.abstracts;

import hotel.models.Model;

import java.sql.Connection;

/**
 * Created by Sherzod on 14.12.2016.
 */
public interface IDatabaseService<T extends Model> extends IService<T> {

    Connection getConnection();

    String getTable();
}
