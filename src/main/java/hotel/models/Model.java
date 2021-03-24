package hotel.models;

import hotel.annotations.Column;
import hotel.annotations.ModelField;

/**
 * Created by kanet on 10-Sen-20.
 */
public abstract class Model {
    private int id;////

    @ModelField
    @Column(columnName = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
