package ejemplobd.accesobd;

import java.sql.Connection;
import java.sql.DriverManager;

public class Accesobd {

    public Accesobd() {
    }

    public Connection getConexion() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/lab1e?" + "user=test&password=test");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}
