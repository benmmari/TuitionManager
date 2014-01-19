/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tuitionmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ben
 */
public class TuitionManager {

    /**
     * @param args the command line arguments
     */
    public TuitionManager() {
        // TODO code application logic here
         try {
             try (Connection conn = getConnection()) {
                 Statement st = conn.createStatement();
                 st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM Students");     

              //get and displays the number of columns
                 ResultSetMetaData rsMetaData = rs.getMetaData();
              int numberOfColumns = rsMetaData.getColumnCount();
                 System.out.println("resultSet MetaData column Count=" + numberOfColumns);

                 st.close();
             }
 } catch(Exception e) {
  System.out.println(e.getMessage());
 }
    }
    
    public static Connection getConnection() {
     String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
     //String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + "C://Volt//Work//School//Annadale.accdb";   
     String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=" + "C://System//School//Annadale.accdb";   
     
     //anime is the database
        String username = ""; //leave blank if none
        String password = ""; //leave blank if none
        try {
      Class.forName(driver);
     } catch (ClassNotFoundException e) {
         System.out.println("nonesense");
     }
        try {
      return DriverManager.getConnection(url, username, password);
     } catch (SQLException e) {
                 System.out.println(e.getMessage());
     }
     return null;
    }
}
