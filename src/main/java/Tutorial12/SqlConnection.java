package Tutorial12;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {


    public static Connection DbConnector() {
        try {
            Connection conn = null;
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\lenovo\\Downloads\\javafx-tasks-master\\TablewithExampleofUserInfo\\src\\main\\resources\\UserDatabase.db");
            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
