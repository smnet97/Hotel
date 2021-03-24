package hotel.services.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

/**
 * Created by kanet on 03-Feb-17.
 */
public class ConnectionManager {

    static final String URL = "jdbc:mysql://localhost:3306/hotel_db?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASSWORD = "Qwerty123%";
    static Connection connection = null;

    public static Connection getConnection(){
        if(connection == null){
            TimeZone timeZone = TimeZone.getTimeZone("Asia/Tashkent");
            TimeZone.setDefault(timeZone);

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Class.forName("com.postgresql.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        System.out.println("connected...");
        return connection;
    }
}
